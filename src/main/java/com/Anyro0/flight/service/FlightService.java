package com.Anyro0.flight.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Anyro0.flight.model.Flight;
import com.Anyro0.flight.repository.FlightRepository;
import com.Anyro0.flight.response.FlightDTO;
import com.Anyro0.flight.response.FlightResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;

    private final String EXTERNAL_API_URL = "https://test.api.amadeus.com/v1/shopping/flight-destinations?"; //ap access url
    private final String authorization = ""; //api access key

    private RestTemplate restTemplate;

    //send request to amodeus api and saves the result to FlightRepository
    public List<Flight> fetchAndSaveFlights(String date, String departure, String arrival, String price) {
        
        //Builds the request
        String apiUrl = buildApiUrl(date, departure, arrival, price);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            //sends request to amodeus api
            ResponseEntity<FlightResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, FlightResponse.class);

            //trys to save result to  flight repository, if no flight information was resived logs it
            if (response != null && response.getBody() != null && response.getBody().getData() != null) {
                List<Flight> flights = response.getBody().getData().stream().map(this::convertToEntity).collect(Collectors.toList());

                flightRepository.saveAll(flights);
                
                return flightRepository.findAll();
            } else {
                log.info("No flights found in the API response");
                return flightRepository.findAll();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return flightRepository.findAll();
        }
    }
    
    //converts incoming api response to workable flight entity
    private Flight convertToEntity(FlightDTO dto) {
        Flight flight = new Flight();
        
        flight.setDate(dto.getDepartureDate());
        flight.setDeparture(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setPrice(dto.getPrice().getTotal());

        return flight;
    }

    //builds the api url depending on what prefrences user selects
    //param. like "&date=" might need to be changed depending on amadeus api requierments
    private String buildApiUrl(String date, String departure, String arrival, String price) {
        StringBuilder apiUrl = new StringBuilder(EXTERNAL_API_URL);
        
        apiUrl.append("origin=").append(departure); //required for the api, otherwise will not get info about flights

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
