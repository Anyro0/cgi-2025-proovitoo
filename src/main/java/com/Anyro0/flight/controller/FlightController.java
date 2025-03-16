package com.Anyro0.flight.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Anyro0.flight.model.Flight;
import com.Anyro0.flight.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam String date,
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam String price) {

        return flightService.fetchAndSaveFlights(date, departure, destination, price);
    }
}
