package com.biletx.controller;

import com.biletx.request.PaymentRequest;
import com.biletx.response.PaymentResponse;
import com.biletx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.payment(paymentRequest));
    }
}
