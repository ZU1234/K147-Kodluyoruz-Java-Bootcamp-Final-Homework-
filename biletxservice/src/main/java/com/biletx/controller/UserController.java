package com.biletx.controller;

import com.biletx.request.LoginRequest;
import com.biletx.request.PaymentRequest;
import com.biletx.request.UserRequest;
import com.biletx.response.PaymentResponse;
import com.biletx.response.UserResponse;
import com.biletx.response.VehicleResponse;
import com.biletx.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        userService.login(loginRequest);
        return ResponseEntity.ok("Giriş yaptınız.");
    }


    @PostMapping(value = "/payment")
    public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(userService.payment(paymentRequest));
    }

}
