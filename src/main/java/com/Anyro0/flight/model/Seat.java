package com.Anyro0.flight.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_row")
    private int row;
    
    private int seatNumber;
    private boolean isOccupied;
    private boolean isWindowSeat;
    private boolean hasExtraLegRoom;
    private boolean isNearExit;
    private boolean isAdjacentToOtherSeat;
    
}
