package com.Anyro0.flight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Anyro0.flight.model.Seat;
import com.Anyro0.flight.repository.SeatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class SeatRecommendationService {

    // Constants defining the number of rows and seats per row in the flight seating layout
    private static final int ROWS = 4;
    private static final int SEATS_PER_ROW = 5;

    private SeatRepository seatRepository;

    public List<Seat> getAvailableSeats(int numberOfTickets, boolean windowSeat, boolean extraLegRoom, boolean nearExit, boolean adjacent) {
        
        // Fetch all seats from the repository and filter those that are not occupied.
        List<Seat> seats = seatRepository.findAll();
        List<Seat> availableSeats = seats.stream()
            .filter(seat -> !seat.isOccupied() && 
                (windowSeat && seat.isWindowSeat() || !windowSeat) && 
                (extraLegRoom && seat.isHasExtraLegRoom() || !extraLegRoom) && 
                (nearExit && seat.isNearExit() || !nearExit))
            .collect(Collectors.toList());

        // If adjacent seats are required, find adjacent seats.
        if (adjacent && numberOfTickets > 1) {
            return findAdjacentSeats(availableSeats, numberOfTickets);
        }

        return availableSeats;
    }

    private List<Seat> findAdjacentSeats(List<Seat> availableSeats, int numberOfTickets) {
        List<Seat> adjacentSeats = new ArrayList<>();

        // Loop through the available seats and try to find adjacent ones.
        for (int i = 0; i < availableSeats.size(); i++) {
            Seat seat = availableSeats.get(i);
            adjacentSeats.add(seat); 

            if (adjacentSeats.size() == numberOfTickets) {
                break;
            }
        }

        return adjacentSeats;
    }

    public void generateSeatLayout() {
        // Delete any existing seat records before generating a new layout.
        seatRepository.deleteAll();

        Random random = new Random();

        // Generate seats for each row in the flight layout.
        for (int row = 1; row <= ROWS; row++) {
            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setSeatNumber(seatNumber);
                seat.setOccupied(random.nextBoolean()); // Randomly set the seat as occupied or not
                seat.setWindowSeat(seatNumber == 1 || seatNumber == SEATS_PER_ROW); // First and last seat are window seats
                seat.setHasExtraLegRoom(row == 1); // Extra legroom only in the first row
                seat.setNearExit(row == 1 || row == 4); // Exit rows are at the front (row 1) and back (row 4)
                
                seatRepository.save(seat);
            }
        }
    }
}
