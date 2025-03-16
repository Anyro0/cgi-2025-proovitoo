package com.Anyro0.flight.Authentication;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenProvider {
    // Secret key used for signing the JWT tokens
    private final String secretKey = "SecretKey";

    // Token validity time in milliseconds(1 hour)
    private final long validityInMilliseconds = 3600000;

    //Generates a JWT token for the given username.
    public String generateToken(String username) {
        
        Date now = new Date();

        
        Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        // Build and return the JWT token
        return JWT.create()
                .withSubject(username) // Set the "subject" as username
                .withIssuedAt(now) // Set the "issued at" time
                .withExpiresAt(expiryDate) // Set the "expiration" time
                .sign(algorithm); // Sign the token
    }
}