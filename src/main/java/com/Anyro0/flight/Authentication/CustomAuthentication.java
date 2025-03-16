package com.Anyro0.flight.Authentication;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.Anyro0.flight.model.User;
import com.Anyro0.flight.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomAuthentication implements AuthenticationProvider{
    
    private UserRepository userRepository;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extract the username and password from the authentication object
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Find the user by username from the repository
        Optional<User> optionalUser = Optional.of(userRepository.findByUsername(username));

        // Check if the user exists
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Validate the password
            if (password.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    //This method checks if this provider supports the specified authentication token class.
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
