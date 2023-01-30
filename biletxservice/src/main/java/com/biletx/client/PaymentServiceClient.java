package com.biletx.client;

import com.biletx.request.PaymentRequest;
import com.biletx.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "accepted-payment-service", url = "http://localhost:8889/payments")
public interface PaymentServiceClient {
    @PostMapping
    PaymentResponse payment(PaymentRequest paymentRequest);
}