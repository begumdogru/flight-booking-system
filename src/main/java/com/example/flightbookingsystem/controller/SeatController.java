package com.example.flightbookingsystem.controller;

import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.model.Seat;
import com.example.flightbookingsystem.service.FlightService;
import com.example.flightbookingsystem.service.SeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
@Api(value = "Seat Api documentation")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    @ApiOperation(value = "Get all seats")
    public ResponseEntity<List<Seat>> getAllFlights() {
        List<Seat> seats = seatService.findAllSeats();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/{seatId}")
    @ApiOperation(value = "Get a seat by ID")
    public ResponseEntity<Seat> getFlightById(@PathVariable Integer seatId) {
        Optional<Seat> seat = seatService.findSeatById(seatId);
        return seat.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add/{flightId}")
    @ApiOperation(value = "Add seat to the flight")
    public ResponseEntity<Seat> addSeatToFlight(@PathVariable Integer flightId, @RequestBody Seat newSeat) {
        Seat addedSeat = seatService.addSeatToFlight(flightId, newSeat);
        return new ResponseEntity<>(addedSeat, HttpStatus.CREATED);
    }

    @PutMapping("/{seatId}")
    @ApiOperation(value = "Update seat")
    public ResponseEntity<Seat> updateSeat(@PathVariable Integer seatId, @RequestBody Seat updatedSeat) {
        Seat updated = seatService.updateSeat(seatId, updatedSeat);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{seatId}")
    @ApiOperation(value = "Remove seat by Id")
    public ResponseEntity<Void> removeSeatById(@PathVariable Integer seatId) {
        boolean deleted = seatService.removeSeatById(seatId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found
        }
    }
    @PostMapping("/book/{seatId}")
    @ApiOperation(value = "Book a seat")
    public ResponseEntity<String> bookSeat(@PathVariable Integer seatId) {
        Optional<Seat> seat = seatService.findSeatById(seatId);
        if (seat.isPresent()) {
            Seat bookedSeat = seatService.bookSeat(seat.get());
            return new ResponseEntity<>("Seat " + bookedSeat.getSeatId() + " has been successfully booked.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Seat with ID " + seatId + " not found.", HttpStatus.NOT_FOUND);
        }
    }


}
