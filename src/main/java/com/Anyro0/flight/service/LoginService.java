package com.Anyro0.flight.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.Anyro0.flight.Authentication.JwtTokenProvider;
import com.Anyro0.flight.response.UserRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public String login(UserRequest userRequest){

        final String username = userRequest.getData().getUsername();
		final String password = userRequest.getData().getPassword();
        
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = jwtTokenProvider.generateToken(username);
            return token;
        } catch (BadCredentialsException e) {
            log.error("Authentication failed for user: {}", username, e);
            throw e;
        }
    }
}
