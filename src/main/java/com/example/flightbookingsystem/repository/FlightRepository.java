package com.example.flightbookingsystem.repository;

import com.example.flightbookingsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> findByFlightId(Integer id);
}
