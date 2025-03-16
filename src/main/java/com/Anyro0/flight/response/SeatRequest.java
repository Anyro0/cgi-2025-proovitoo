package com.Anyro0.flight.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SeatRequest {
    private int numberOfTickets;
    private boolean windowSeat;
    private boolean extraLegRoom;
    private boolean nearExit;
    private boolean adjacent;
}
