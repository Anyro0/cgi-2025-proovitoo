package com.Anyro0.flight.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anyro0.flight.response.UserRequest;
import com.Anyro0.flight.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        Boolean registration = registrationService.register(userRequest);
        if (registration){
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        
    }

    
}
