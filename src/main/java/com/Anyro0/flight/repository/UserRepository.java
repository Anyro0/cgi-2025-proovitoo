package com.Anyro0.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Anyro0.flight.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByUsername(String username);

}
