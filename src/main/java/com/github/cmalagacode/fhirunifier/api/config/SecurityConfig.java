package com.github.cmalagacode.fhirunifier.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // don’t require login
                .csrf(csrf -> csrf.disable()) // optional for APIs
                .oauth2Login(oauth2 -> oauth2.disable()); // disable interactive login
        return http.build();
    }
}

