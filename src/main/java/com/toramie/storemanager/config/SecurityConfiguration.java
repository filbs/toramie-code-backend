package com.toramie.storemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //API testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll() //public authorization
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
