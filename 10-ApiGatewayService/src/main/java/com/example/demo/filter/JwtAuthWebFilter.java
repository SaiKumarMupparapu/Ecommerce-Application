package com.example.demo.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.security.JwtUtil;

import reactor.core.publisher.Mono;

@Component
@Import(JwtUtil.class)
public class JwtAuthWebFilter implements WebFilter {
	@Autowired
    private com.security.JwtUtil jwtUtil;


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		// TODO Auto-generated method stub
		String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if(header !=null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			 String username = jwtUtil.extractUsername(token);
			Boolean validateToken = jwtUtil.validateToken(token, username);
			if(validateToken) {

			List<SimpleGrantedAuthority> authorities = 
					jwtUtil.extractAuthorities(token);
			
			List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.toList();
			
			ServerWebExchange mutedExchange = exchange.mutate()
			.request(builder->builder
					.header("X-Username", username)
					.header("X-Roles", String.join(",", roles))
					)
			.build();
			
			
			UsernamePasswordAuthenticationToken auth =
					new UsernamePasswordAuthenticationToken(username,null, authorities);
			return chain.filter(mutedExchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
			}
		}
		
		return chain.filter(exchange);
	}
}
