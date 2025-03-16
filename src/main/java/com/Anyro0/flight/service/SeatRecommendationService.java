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

    private static final int ROWS = 4;
    private static final int SEATS_PER_ROW = 5;

    private SeatRepository seatRepository;

    public List<Seat> getAvailableSeats(int numberOfTickets, boolean windowSeat, boolean extraLegRoom, boolean nearExit, boolean adjacent) {
        
        List<Seat> seats = seatRepository.findAll();
        List<Seat> availableSeats = seats.stream().filter(seat -> !seat.isOccupied() && (windowSeat && seat.isWindowSeat() || !windowSeat) && (extraLegRoom && seat.isHasExtraLegRoom() || !extraLegRoom) && (nearExit && seat.isNearExit() || !nearExit)).collect(Collectors.toList());


        if (adjacent && numberOfTickets > 1) {
            return findAdjacentSeats(availableSeats, numberOfTickets);
        }

        return availableSeats;
    }

    private List<Seat> findAdjacentSeats(List<Seat> availableSeats, int numberOfTickets) {
        List<Seat> adjacentSeats = new ArrayList<>();


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
        seatRepository.deleteAll();

        Random random = new Random();

        for (int row = 1; row <= ROWS; row++) {
            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW; seatNumber++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setSeatNumber(seatNumber);
                seat.setOccupied(random.nextBoolean()); // Simulate random seat occupation
                seat.setWindowSeat(seatNumber == 1 || seatNumber == SEATS_PER_ROW); // Assuming window seats are at both ends
                seat.setHasExtraLegRoom(row == 1); // Assuming extra legroom is in the first row
                seat.setNearExit(row == 1 || row == 4); // Assuming exit rows are at the front and back
                seatRepository.save(seat); // Save to database
            }
        }
    }
}
