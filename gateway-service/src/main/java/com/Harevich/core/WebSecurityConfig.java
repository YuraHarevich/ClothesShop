package com.Harevich.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {
@Bean
public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
            .pathMatchers("/api/v1/auth/login").permitAll()
            .pathMatchers("/api/v1/auth/registration").permitAll()
            .pathMatchers(HttpMethod.GET,"/api/v1/clothes/**").permitAll()
            .pathMatchers(HttpMethod.GET,"/api/v1/orders/**").permitAll()
            .anyExchange().authenticated()
    );
    http.oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
            .jwt(Customizer.withDefaults()));
    http.csrf(ServerHttpSecurity.CsrfSpec::disable);
    return http.build();
}
}