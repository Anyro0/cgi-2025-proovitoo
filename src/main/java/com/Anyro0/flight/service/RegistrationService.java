package com.Anyro0.flight.service;

import org.springframework.stereotype.Service;

import com.Anyro0.flight.model.User;
import com.Anyro0.flight.repository.UserRepository;
import com.Anyro0.flight.response.UserRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final UserValidationService userValidationService;

    private static final Boolean registration_successful = true;
    private static final Boolean registration_unsuccessful = false;

    public Boolean register(UserRequest userRequest){

        String username = userRequest.getData().getUsername();
        String password = userRequest.getData().getPassword();
        
        Boolean validation = userValidationService.validateUser(username);

        if(validation){
            final User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);

            log.info("{} registered successfully", username);
            return registration_successful;
        } else {
            return registration_unsuccessful;
        }

        
    }
}
