package com.example.flightbookingsystem.service;

import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Find a flight by ID
    public Optional<Flight> findFlightById(Integer flightId) {
        return flightRepository.findById(flightId);
    }

    // Find all flights
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    // Save a flight
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Update a flight
    @Transactional
    public Flight updateFlight(Integer flightId, Flight updatedFlight) {
        Optional<Flight> existingFlightOptional = flightRepository.findById(flightId);

        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();
            // Update the properties of the existing flight with the new values
            existingFlight.setRoute(updatedFlight.getRoute());
            existingFlight.setDepartTime(updatedFlight.getDepartTime());
            existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
            existingFlight.setReservationCode(updatedFlight.getReservationCode());

            // Save and return the updated flight
            return flightRepository.save(existingFlight);
        } else {
            // Flight with the given ID not found
            throw new RuntimeException("Flight not found with ID: " + flightId);
        }
    }
}
