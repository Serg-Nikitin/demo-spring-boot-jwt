package ru.nikitin.jwt.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import ru.nikitin.jwt.service.function.ConvertTokenToAuthorization;

@Data
@Component
public class JwtConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {
    @Value("${jwt.type}")
    private String bearer;

    @Autowired
    JwtFilter filter;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.exceptionHandling(c -> c.authenticationEntryPoint(filter.getEntryPoint()));
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }
}
