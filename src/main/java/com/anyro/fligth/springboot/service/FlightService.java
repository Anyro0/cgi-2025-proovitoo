package com.anyro.fligth.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anyro.fligth.springboot.model.Flight;
import com.anyro.fligth.springboot.repository.FlightRepository;

//finishing after deciding which flight data API to use(should also add 2 way flights), maybe also a way to keep airline name empty(not used)

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private final String EXTERNAL_API_URL = "";

    @Autowired
    private RestTemplate restTemplate;

    public List<Flight> fetchAndSaveFlights(String dateString, String departure, String arrival, String airline) {

        String apiUrl = EXTERNAL_API_URL + "" + dateString + "" + departure + "" + arrival + "" + airline;

        Flight[] flights = restTemplate.getForObject(apiUrl, Flight[].class);

        if (flights != null) {
            for (Flight flight : flights) {
                flightRepository.save(flight);
            }
        }

        return flightRepository.findAll();
    }
    
}
