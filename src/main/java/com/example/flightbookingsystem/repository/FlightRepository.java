package com.example.flightbookingsystem.repository;

import com.example.flightbookingsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
