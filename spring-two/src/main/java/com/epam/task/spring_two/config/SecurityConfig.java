package com.epam.task.spring_two.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                       .requestMatchers("/api/books/all").permitAll()
                        .requestMatchers("/api/books/unsafe").permitAll()
                        .requestMatchers("/api/books/logic-error").permitAll()
                        .requestMatchers("/api/books/get**").authenticated()
                        .requestMatchers("/api/books/updateBookById/**").hasAuthority("SCOPE_update:book")
                        .requestMatchers("/api/books/deleteBookById/**").hasAuthority("SCOPE_update:book")
                        .requestMatchers("/api/books/create").hasAuthority("SCOPE_update:book")
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String jwkSetUri = "https://dev-w1csf1wc8blmmzi2.us.auth0.com/.well-known/jwks.json";
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

}
