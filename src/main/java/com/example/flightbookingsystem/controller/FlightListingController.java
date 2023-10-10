package com.example.flightbookingsystem.controller;

import com.example.flightbookingsystem.dto.FlightListingInfo;
import com.example.flightbookingsystem.service.FlightListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightListingController {

    private final FlightListingService flightListingService;

    @Autowired
    public FlightListingController(FlightListingService flightListingService) {
        this.flightListingService = flightListingService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<FlightListingInfo>> listFlights() {
        List<FlightListingInfo> flightListingInfoList = flightListingService.listFlights();
        return new ResponseEntity<>(flightListingInfoList, HttpStatus.OK);
    }
}
