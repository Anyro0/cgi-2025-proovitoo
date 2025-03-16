package com.Anyro0.flight.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Flight> getFlights(@RequestBody FlightRequest flightRequest){
        return flightService.fetchAndSaveFlights(flightRequest.getDate(), flightRequest.getDeparture(), flightRequest.getDestination(), flightRequest.getPrice());
    }
}
