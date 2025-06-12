package com.security;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
//    @Value("${jwt.secret}")
//    private String secret;
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
        List<String> roles = extractAllClaims(token).get("roles", List.class);
        return roles.stream()
            .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role) // Ensure prefix
            .map(SimpleGrantedAuthority::new)
            .toList();
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
        public String extractRole(String token) {
            return extractAllClaims(token).get("role", String.class);
        }
    
        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }
    
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            return claimsResolver.apply(extractAllClaims(token));
        }
    
       
    
        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }
    
        public Boolean validateToken(String token, String username) {
            return (extractUsername(token).equals(username) && isTokenExpired(token));
        }
    
        public String generateToken(String username, String role) {
            return Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        }
}
