package com.Anyro0.flight.Authentication;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenProvider {
    private final String secretKey = "SecretKey";
    private final long validityInMilliseconds = 3600000;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

        
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        return JWT.create()
                .withSubject(username) // Set the subject (username)
                .withIssuedAt(now) // Set the issued time
                .withExpiresAt(expiryDate) // Set expiration time
                .sign(algorithm); // Sign the token using the algorithm
    }
}
