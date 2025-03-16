package com.Anyro0.flight.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Anyro0.flight.model.Flight;
import com.Anyro0.flight.response.FlightRequest;
import com.Anyro0.flight.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<?> getFlights(@RequestBody FlightRequest flightRequest){
        List<Flight> flights =  flightService.fetchAndSaveFlights(flightRequest.getDate(), flightRequest.getDeparture(), flightRequest.getDestination(), flightRequest.getPrice());
        
        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
    
}

