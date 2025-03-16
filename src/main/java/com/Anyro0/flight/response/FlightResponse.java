package com.Anyro0.flight.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FlightResponse {
    private List<FlightDTO> data;
}
