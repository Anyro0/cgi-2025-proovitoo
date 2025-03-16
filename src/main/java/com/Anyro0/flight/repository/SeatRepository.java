package com.Anyro0.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Anyro0.flight.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
