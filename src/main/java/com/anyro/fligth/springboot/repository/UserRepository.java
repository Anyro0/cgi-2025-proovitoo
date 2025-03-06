package com.anyro.fligth.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anyro.fligth.springboot.model.User;

// rimmel asghar
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}
