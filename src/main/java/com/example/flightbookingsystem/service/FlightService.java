package com.example.flightbookingsystem.service;

import com.example.flightbookingsystem.model.Flight;
import com.example.flightbookingsystem.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    public Flight getFlightById(Integer id){
         return flightRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flight is not found with id:" + id));

    }

}
