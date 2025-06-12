package com.example.demo.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
    private com.security.JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
System.out.println(request);
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
 System.err.println(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String role = jwtUtil.extractRole(jwt);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            new User(username, "", Collections.singleton(() -> "ROLE_" + role)),
                            null,
                            Collections.singleton(() -> "ROLE_" + role)
                    );
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        chain.doFilter(request, response);
    }
}
