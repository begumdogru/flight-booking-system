package com.example.flightbookingsystem.controller;


import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/flight")
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) Integer id) {
        try {
            List<Flight> flights = new ArrayList<Flight>();
            if (id == null) {
                flightRepository.findAll().forEach(flights::add);
            } else {
                flightRepository.findByFlightId(id).forEach(flights::add);
            }
            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


