package com.Anyro0.flight.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anyro0.flight.model.Seat;
import com.Anyro0.flight.response.SeatRequest;
import com.Anyro0.flight.service.SeatRecommendationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final SeatRecommendationService seatRecommendationService;

    @GetMapping()
    public ResponseEntity<?> recommendSeats(@RequestBody SeatRequest seatRequest){

        seatRecommendationService.generateSeatLayout();
        List<Seat> availableSeats = seatRecommendationService.getAvailableSeats(seatRequest.getNumberOfTickets(), seatRequest.isWindowSeat(), seatRequest.isExtraLegRoom(), seatRequest.isNearExit(), seatRequest.isAdjacent());

    if (availableSeats.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(availableSeats, HttpStatus.OK);
}

}
