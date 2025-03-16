package com.Anyro0.flight.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightRequest {
    private String date;
    private String departure;
    private String destination;
    private String price;
}