//package com.example.demo.filter;
//
//import java.io.IOException;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.demo.service.JwtService;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//	@Autowired
//	private JwtService jwtService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		// TODO Auto-generated method stub
//		String authHeader = request.getHeader("Authorization");
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		final String token = authHeader.substring(7);
//			String email = jwtService.extractUsername(token);
//
//
//		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			try {
//				jwtService.validateToken(token);
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				throw new RuntimeException(e.getMessage());
//			}
//
//			List<SimpleGrantedAuthority> authorities = jwtService.extractRoles(token).stream()
//					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).toList();
//
//			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null,
//					authorities);
//
//			SecurityContextHolder.getContext().setAuthentication(authToken);
//
//		}
//
//		filterChain.doFilter(request, response);
//
//	}
//
//}
