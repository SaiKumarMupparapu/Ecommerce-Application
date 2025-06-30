package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;



@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private com.example.demo.filter.JwtAuthWebFilter jwtAuthWebFilter;


    @Bean
     SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    	System.err.println("INSIDE THE securityWebFilterChain METHOD"+"\n http");
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(ex -> ex
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/api/product/**").authenticated()
                .anyExchange().authenticated()
            )
            .addFilterAt(jwtAuthWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .formLogin(form->form.disable())
            .httpBasic(basic->basic.disable())
            .build();
    }
}
