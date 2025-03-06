package com.anyro.fligth.springboot.security.service;

import com.anyro.fligth.springboot.model.User;
import com.anyro.fligth.springboot.security.dto.AuthenticatedUserDto;
import com.anyro.fligth.springboot.security.dto.RegistrationRequest;
import com.anyro.fligth.springboot.security.dto.RegistrationResponse;

// rimmel asghar
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
