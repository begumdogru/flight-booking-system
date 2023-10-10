package com.example.flightbookingsystem.controller;

import com.example.flightbookingsystem.model.Payment;
import com.example.flightbookingsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/makePayment")
    public ResponseEntity<Payment> makePayment(
            @RequestParam Integer passengerId,
            @RequestParam Integer seatId,
            @RequestParam double paymentAmount) {
        try {
            Payment payment = paymentService.makePayment(passengerId, seatId, paymentAmount);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
