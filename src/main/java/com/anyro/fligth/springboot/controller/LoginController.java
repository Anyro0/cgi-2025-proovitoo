package com.anyro.fligth.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anyro.fligth.springboot.security.dto.LoginRequest;
import com.anyro.fligth.springboot.security.dto.LoginResponse;
import com.anyro.fligth.springboot.security.jwt.JwtTokenService;

import javax.validation.Valid;

// rimmel asghar
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenService jwtTokenService;

	@PostMapping
	public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

		final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

		return ResponseEntity.ok(loginResponse);
	}

}
