package com.biletx.controller;

import com.biletx.model.Vehicle;
import com.biletx.response.TotalTicketsAndTotalPricesByVehicleResponse;
import com.biletx.response.VehicleResponse;
import com.biletx.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.save(vehicle));
    }



    @PostMapping(value = "/{id}")
    public ResponseEntity<VehicleResponse> vehicleCancellationById(@PathVariable Integer id) {
        return ResponseEntity.ok(vehicleService.cancellationVehicle(id));
    }
    @GetMapping(value = "/{vehicleId}")
    public ResponseEntity<TotalTicketsAndTotalPricesByVehicleResponse> totalTicketsAndTotalPricesByVehicle(@PathVariable int vehicleId){
        return ResponseEntity.ok(vehicleService.totalTicketsAndTotalPricesByVehicle(vehicleId));
    }
}
