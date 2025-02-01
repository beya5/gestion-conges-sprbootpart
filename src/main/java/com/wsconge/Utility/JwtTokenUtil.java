package com.wsconge.Utility;

import com.wsconge.entities.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET_STRING = "serin"; // Use a secure method for storing secrets
    private static final Key SECRET_KEY = generateKey(SECRET_STRING);
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    /**
     * Generate JWT Token for a user
     * @param utilisateur User details
     * @return JWT Token
     */
    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .setSubject(utilisateur.getNom())
                .claim("mdp", utilisateur.getMdp())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Extract username from token
     * @param token JWT token
     * @return Username
     */
    public static String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * Validate a token
     * @param token JWT token
     * @param username Username to validate
     * @return Boolean - true if valid, false otherwise
     */
    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }

    /**
     * Check if the token is expired
     * @param token JWT token
     * @return Boolean - true if expired
     */
    private boolean isTokenExpired(String token) {
        Date expiration = parseClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    /**
     * Parse claims from token
     * @param token JWT token
     * @return Claims object
     */
    private static Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generate signing key using SHA-256
     * @param secret Secret string
     * @return Key object
     */
    private static Key generateKey(String secret) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedKey = digest.digest(secret.getBytes(StandardCharsets.UTF_8));
            return new SecretKeySpec(hashedKey, SignatureAlgorithm.HS256.getJcaName());
        } catch (Exception e) {
            throw new RuntimeException("Error while generating key", e);
        }
    }
}