//package com.example.demo.service;
//
//import java.security.Key;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtService {
//
//	public static final String SECRET ="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//
//    // Validate JWT (throws exception if invalid)
//    public void validateToken(String token) {
//        extractAllClaims(token);
//    }
//
//    // Get username/email from token
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // Get list of role names from token
//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        Object roles = claims.get("roles");
//
//        if (roles instanceof List<?>) {
//            return ((List<?>) roles).stream()
//                    .map(Object::toString)
//                    .toList();
//        }
//        return Collections.emptyList();
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
//
