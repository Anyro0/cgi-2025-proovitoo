package com.anyro.fligth.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anyro.fligth.springboot.model.Flight;
import com.anyro.fligth.springboot.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam String dateString,
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam String airline) {

        return flightService.fetchAndSaveFlights(dateString, departure, arrival, airline);
    }
}
