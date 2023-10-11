package com.example.flightbookingsystem.controller;

import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
@Api(value = "Flight Api Documentation")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    @ApiOperation(value = "Get all flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.findAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    @ApiOperation(value = "Get a flight by ID")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer flightId) {
        Optional<Flight> flight = flightService.findFlightById(flightId);
        return flight.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create a new flight")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.saveFlight(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    // Update a flight by ID
    @PutMapping("/{flightId}")
    @ApiOperation(value = "Update a flight by ID")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer flightId, @RequestBody Flight updatedFlight) {
        Flight updated = flightService.updateFlight(flightId, updatedFlight);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/{flightId}")
    @ApiOperation(value = "Delete a flight")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer flightId) {
        boolean deleted = flightService.deleteFlightById(flightId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found
        }
    }

}
