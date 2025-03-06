package com.anyro.fligth.springboot.security.dto;

import com.anyro.fligth.springboot.model.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// rimmel asghar
@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {

	private String name;

	private String username;

	private String password;

	private UserRole userRole;

	public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

}
