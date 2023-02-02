package com.biletx.controller;

import com.biletx.enums.VehicleType;
import com.biletx.request.RouteRequest;
import com.biletx.request.TicketRequest;
import com.biletx.response.BasketResponse;
import com.biletx.response.VehicleResponse;
import com.biletx.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> findAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @GetMapping(value = "/{fromWhere}")
    public ResponseEntity<List<VehicleResponse>> findAllByRoute(@RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(vehicleService.getAllByRoute(routeRequest));
    }

    @GetMapping(value = "/bydate")
    public ResponseEntity<List<VehicleResponse>> findAllByDepartureTime(@RequestBody String departureTime
    ) {
        return ResponseEntity.ok(vehicleService.getAllByDate(departureTime));
    }

    @GetMapping(value = "/vehicletype")
    public ResponseEntity<List<VehicleResponse>> findAllByVehicleType(@RequestBody VehicleType vehicleType) {
        return ResponseEntity.ok(vehicleService.getAllByVehicleType(vehicleType));
    }

    @PostMapping(value = "/basket")
    public ResponseEntity<List<BasketResponse>> basketAddTicket(@RequestBody TicketRequest ticket) {
        return ResponseEntity.ok(vehicleService.sepetAdd(ticket));
    }

    @GetMapping(value = "/basket/{id}")
    public ResponseEntity<List<BasketResponse>> getAllBasketByUserId(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.getAllBasketByUserId(id));
    }

}

