package com.Anyro0.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Anyro0.flight.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
