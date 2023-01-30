package com.biletx.converter;

import com.biletx.model.Vehicle;
import com.biletx.response.VehicleResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleConverter {
    public List<VehicleResponse> convert(List<Vehicle> vehicleList) {
        List<VehicleResponse> responseList = new ArrayList<>();
        vehicleList.forEach(vehicle -> responseList.add(convert(vehicle)));
        return responseList;
    }

    public VehicleResponse convert(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setNo(vehicle.getNo());
        response.setStatus(vehicle.getStatus());
        response.setVehicleType(vehicle.getVehicleType());
        response.setFromWhere(vehicle.getFromWhere());
        response.setWhereTo(vehicle.getWhereTo());
        response.setRidership(vehicle.getRidership());
        response.setDepartureClock(vehicle.getDepartureClock());
        response.setDepartureTime(String.valueOf(vehicle.getDepartureTime()));
        response.setClockOfArrival(vehicle.getClockOfArrival());
        response.setEmptySeat(vehicle.getEmptySeat());
        response.setPrice(vehicle.getPrice());
        return response;
    }
}
