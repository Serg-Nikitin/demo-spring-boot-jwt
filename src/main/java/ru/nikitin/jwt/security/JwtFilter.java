package ru.nikitin.jwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.nikitin.jwt.model.exception.JwtAuthException;
import ru.nikitin.jwt.service.function.ConvertTokenToAuthorization;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Data
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.type}")
    private String bearer;
    @Value("${jwt.private.url}")
    private String url;
    @Autowired
    private ConvertTokenToAuthorization converter;

    private AuthenticationEntryPoint entryPoint = ((request, response, authException) -> {
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, bearer.trim());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    });

    private SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
    private SecurityContextRepository repository = new RequestAttributeSecurityContextRepository();
    private Predicate<? super String> checkToken;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
        if (requestMatcher.matches(request)) {

            Optional<Authentication> auth = Optional.empty();
            try {
                auth = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                        .filter(str -> str.startsWith(bearer))
                        .map(str -> str.substring(bearer.length()))
                        .filter(checkToken)
                        .map(converter);

            } catch (RuntimeException e) {
                sendUnAuthorized(request, response);
                log.error("doFilterInternal", e);
                return;
            }

            if (auth.isPresent() && auth.get() instanceof UsernamePasswordAuthenticationToken authUser) {
                authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext newContext = strategy.createEmptyContext();
                newContext.setAuthentication(authUser);
                SecurityContextHolder.getContext().setAuthentication(authUser);
                strategy.setContext(newContext);
                repository.saveContext(newContext, request, response);

            } else {
                sendUnAuthorized(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnAuthorized(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        strategy.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        entryPoint.commence(request, response, new JwtAuthException("Bad credentials"));
    }
}

