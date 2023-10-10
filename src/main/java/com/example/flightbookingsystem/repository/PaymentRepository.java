package com.example.flightbookingsystem.repository;

import com.example.flightbookingsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

