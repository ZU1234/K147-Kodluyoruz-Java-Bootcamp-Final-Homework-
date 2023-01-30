package com.biletx.converter;

import com.biletx.model.Basket;
import com.biletx.response.BasketResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasketConverter {
    public List<BasketResponse> convert(List<Basket> basketList) {
        List<BasketResponse> responseList = new ArrayList<>();
        basketList.forEach(basket -> responseList.add(convert(basket)));
        return responseList;
    }

    public BasketResponse convert(Basket basket) {
        BasketResponse response = new BasketResponse();
        response.setUserId(basket.getUserId());
        response.setVehicleNo(basket.getVehicle().getNo());
        response.setStatus(basket.getVehicle().getStatus());
        response.setVehicleType(basket.getVehicle().getVehicleType());
        response.setFromWhere(basket.getVehicle().getFromWhere());
        response.setWhereTo(basket.getVehicle().getWhereTo());
        response.setDepartureTime(basket.getVehicle().getDepartureTime());
        response.setDepartureClock(basket.getVehicle().getDepartureClock());
        response.setPassengerName(basket.getPassengerName());
        response.setGender(basket.getGender());
        response.setPrice(basket.getVehicle().getPrice());
        return response;

    }
}
