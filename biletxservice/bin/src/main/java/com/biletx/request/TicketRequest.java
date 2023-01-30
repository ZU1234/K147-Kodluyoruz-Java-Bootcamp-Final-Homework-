package com.biletx.request;

import com.biletx.enums.GenderType;

public class TicketRequest {
    private Integer userId;
    private Integer vehicleId;
    private String passengerName;

    private GenderType gender;

    public TicketRequest() {
    }

    public TicketRequest(Integer vehicleId, String passengerName, GenderType gender) {

        this.vehicleId = vehicleId;
        this.passengerName = passengerName;
        this.gender = gender;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TicketRequest{" +
                "userId=" + userId +
                ", vehicleId=" + vehicleId +
                ", passengerName='" + passengerName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
