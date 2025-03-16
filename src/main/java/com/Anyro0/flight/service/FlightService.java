package com.Anyro0.flight.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Anyro0.flight.model.Flight;
import com.Anyro0.flight.repository.FlightRepository;
import com.Anyro0.flight.response.FlightDTO;
import com.Anyro0.flight.response.FlightResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private final String EXTERNAL_API_URL = "https://test.api.amadeus.com/v1/shopping/flight-destinations?";
    private final String authorization = "";

    @Autowired
    private RestTemplate restTemplate;

    public List<Flight> fetchAndSaveFlights(String date, String departure, String arrival, String price) {

        String apiUrl = buildApiUrl(date, departure, arrival, price);

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", authorization); 
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<FlightResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, FlightResponse.class);

            if (response != null && response.getBody() != null && response.getBody().getData() != null) {
                List<Flight> flights = response.getBody().getData().stream()
                        .map(this::convertToEntity)
                        .collect(Collectors.toList());

                flightRepository.saveAll(flights);
                
                return flightRepository.findAll();
            } else {
                log.info("No flights found in the API response");
                return flightRepository.findAll();
            }

        } catch (Exception e) {
            // Log errors
            e.printStackTrace();
            return flightRepository.findAll();
        }
    }

    private Flight convertToEntity(FlightDTO dto) {
        Flight flight = new Flight();
        
        flight.setDate(dto.getDepartureDate());
        flight.setDeparture(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setPrice(dto.getPrice().getTotal());

        return flight;
    }

    
    private String buildApiUrl(String date, String departure, String arrival, String price) {
        StringBuilder apiUrl = new StringBuilder(EXTERNAL_API_URL);
        
        apiUrl.append("origin=").append(departure);

        if (price != null && !price.isEmpty()) {
            apiUrl.append("&maxPrice=").append(date);
        }

        if (date != null && !date.isEmpty()) {
            apiUrl.append("&date=").append(date);
        }
        
        if (departure != null && !departure.isEmpty()) {
            apiUrl.append("&departure=").append(departure);
        }
        
        if (arrival != null && !arrival.isEmpty()) {
            apiUrl.append("&arrival=").append(arrival);
        }
        

        return apiUrl.toString();
    }
    
}
