package com.anyro.fligth.springboot.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.anyro.fligth.springboot.model.User;
import com.anyro.fligth.springboot.security.dto.AuthenticatedUserDto;
import com.anyro.fligth.springboot.security.dto.RegistrationRequest;

// rimmel asghar
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
