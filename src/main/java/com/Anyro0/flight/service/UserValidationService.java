package com.Anyro0.flight.service;

import org.springframework.stereotype.Service;

import com.Anyro0.flight.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

	// Constants for error messages and return values
	private static final String username_already_exists = "username_already_exists";
	private static final Boolean username_does_not_exit = true;

	private final UserRepository userRepository;


	public boolean validateUser(String username) {
		return checkUsername(username);
	}

	private boolean checkUsername(String username) {

		// Check if the username already exists in the database
        final boolean existsByUsername = userRepository.existsByUsername(username);

        if (existsByUsername) {
            log.warn("{} is already being used!", username); 
            throw new RuntimeException(username_already_exists);
        }

        return username_does_not_exit;
    }
}
