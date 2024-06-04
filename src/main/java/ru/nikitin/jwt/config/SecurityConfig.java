package ru.nikitin.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.nikitin.jwt.security.JwtConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtConfigurer configurer
    ) throws Exception {
        return http
//                .headers(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/private/admin/**").permitAll()/*.hasRole("ADMIN")*/
                                .requestMatchers("/private/users/**").permitAll()/*.hasRole("USER")*/
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .anyRequest().authenticated())
                .with(configurer, Customizer.withDefaults())
                .build();
    }
    private static final String[] AUTH_WHITELIST = {
//            "/public/authenticate",
//            "/public/swagger-resources/**",
            "/public/**",
            "/public/swagger-ui/**",
            "/v3/api-docs/**",
            "/public/swagger-ui/index.html"
    };
}
