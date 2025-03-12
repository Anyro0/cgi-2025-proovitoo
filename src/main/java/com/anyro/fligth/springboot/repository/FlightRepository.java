package com.anyro.fligth.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anyro.fligth.springboot.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    
}
