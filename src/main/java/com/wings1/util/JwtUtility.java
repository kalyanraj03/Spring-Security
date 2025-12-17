package com.wings1.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {

    private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey123"; // should be minimum 256 bits

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 1;


    private Key getSigningkey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims,username);

    }

    private String createToken(Map<String, Object> claims, String username) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                    .signWith(getSigningkey(), SignatureAlgorithm.HS256)
                    .compact();
    }


    public String extractUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getAllClaimsFromToken(token).get("role");
    }

    public Date extractExpiration(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }


    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        String username = userDetails.getUsername();

        return (username.equals(extractUsername(token)) && !isTokenExpired(token));


    }


}
