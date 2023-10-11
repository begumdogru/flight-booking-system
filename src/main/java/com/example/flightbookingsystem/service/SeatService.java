package com.example.flightbookingsystem.service;

import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.model.Seat;
import com.example.flightbookingsystem.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    private final FlightService flightService;

    public SeatService(SeatRepository seatRepository, FlightService flightService) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
    }

    public Optional<Seat> findSeatById(Integer flightId) {
        return seatRepository.findById(flightId);
    }

    // Find all flights
    public List<Seat> findAllSeats() {
        return seatRepository.findAll();
    }
    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }
    public Seat addSeatToFlight(Integer flightId, Seat newSeat) {
        // Retrieve the flight by ID
        Flight flight = flightService.findFlightById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));

        // Set the flight for the new seat
        newSeat.setFlight(flight);

        // Save the new seat
        return seatRepository.save(newSeat);
    }
    @Transactional
    public boolean removeSeatById(Integer seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (seat.isPresent()) {
            seatRepository.deleteById(seatId);
            return true;
        } else {
            return false; // Seat not found
        }
    }


    @Transactional
    public Seat updateSeat(Integer seatId, Seat updatedSeat) {
        Optional<Seat> existingSeatOptional = seatRepository.findById(seatId);

        if (existingSeatOptional.isPresent()) {
            Seat existingSeat = existingSeatOptional.get();
            // Update the properties of the existing flight with the new values
            existingSeat.setSeatId(updatedSeat.getSeatId());
            existingSeat.setBooked(updatedSeat.getBooked());
            existingSeat.setSold(updatedSeat.isSold());
            existingSeat.setPrice(updatedSeat.getPrice());
            existingSeat.setFlight(updatedSeat.getFlight());

            // Save and return the updated flight
            return seatRepository.save(existingSeat);
        } else {
            // Flight with the given ID not found
            throw new RuntimeException("Seat not found with ID: " + seatId);
        }
    }
    @Transactional
    public boolean deleteSeatById(Integer seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);

        if (seat.isPresent()) {
            seatRepository.deleteById(seatId);
            return true;
        } else {
            return false;
        }
    }
    public Seat bookSeat(Seat seat) {
        if (!seat.getBooked()) {
            seat.setBooked(true);
            return seatRepository.save(seat);
        }

        return seat; // The seat is already booked
    }

}
