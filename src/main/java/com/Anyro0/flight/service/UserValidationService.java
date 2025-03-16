package com.Anyro0.flight.service;

import org.springframework.stereotype.Service;

import com.Anyro0.flight.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {


	private static final String username_already_exists = "username_already_exists";

	private final UserRepository userRepository;

	public void validateUser(String username) {

		checkUsername(username);
	}

	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			log.warn("{} is already being used!", username);
			
			throw new RuntimeException(username_already_exists);
		}

	}

}
