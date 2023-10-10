package com.example.flightbookingsystem.service;

import com.example.flightbookingsystem.dto.FlightListingInfo;
import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.model.Seat;
import com.example.flightbookingsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightListingService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightListingService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<FlightListingInfo> listFlights() {
        List<Flight> flights = flightRepository.findAll();
        return convertToFlightListingInfo(flights);
    }

    private List<FlightListingInfo> convertToFlightListingInfo(List<Flight> flights) {
        List<FlightListingInfo> flightListingInfoList = new ArrayList<>();
        for (Flight flight : flights) {
            FlightListingInfo info = new FlightListingInfo();
            info.setName(flight.getFlightId().toString());
            info.setDescription(flight.getRoute());
            // Implement logic to calculate available seats based on your business rules
            info.setAvailableSeats(calculateAvailableSeats(flight));
            info.setPrice(calculateTotalPrice(flight));
            flightListingInfoList.add(info);
        }
        return flightListingInfoList;
    }

    // Implement the logic to calculate available seats based on your business rules
    private int calculateAvailableSeats(Flight flight) {
        // Get the list of seats associated with the flight
        List<Seat> seats = flight.getSeats();

        // Initialize a counter for available seats
        int availableSeatsCount = 0;

        // Iterate through the seats and count available seats
        for (Seat seat : seats) {
            if (!seat.isSold()) {
                availableSeatsCount++;
            }
        }

        return availableSeatsCount;
    }
    private double calculateTotalPrice(Flight flight) {
        List<Seat> seats = flight.getSeats();
        double totalPrice = 0.0;

        for (Seat seat : seats) {
            totalPrice += seat.getPrice();
        }

        return totalPrice;
    }



}
