package com.Anyro0.flight.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {

    @JsonProperty("type")
    private String type;  //tpye of flight

    @JsonProperty("origin")
    private String origin; //departure airport

    @JsonProperty("destination")
    private String destination; //destination airport
    
    @JsonProperty("departureDate")
    private String departureDate;  //departure date
    
    @JsonProperty("returnDate")
    private String returnDate; //return date
    
    private Price price;

    @Getter
    @Setter
    public static class Price {
        @JsonProperty("total")
        private String total; //total price of a ticket
    }
}
