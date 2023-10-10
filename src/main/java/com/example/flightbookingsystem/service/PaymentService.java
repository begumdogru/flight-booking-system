package com.example.flightbookingsystem.service;

import com.example.flightbookingsystem.model.Payment;
import com.example.flightbookingsystem.model.Seat;
import com.example.flightbookingsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final SeatService seatService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, SeatService seatService) {
        this.paymentRepository = paymentRepository;
        this.seatService = seatService;
    }

    @Transactional
    public Payment makePayment(Integer passengerId, Integer seatId, double paymentAmount) {
        Seat seat = seatService.findSeatById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isSold() || seat.getBooked()) {
            throw new RuntimeException("Seat is not available for booking.");
        }

        Payment payment = new Payment();
        payment.setPassengerId(passengerId);
        payment.setSeatId(seatId);
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentStatus("SUCCESS");

        // Update seat status to sold
        seat.setSold(true);
        seatService.saveSeat(seat);

        // Save the payment transaction
        return paymentRepository.save(payment);
    }
}

