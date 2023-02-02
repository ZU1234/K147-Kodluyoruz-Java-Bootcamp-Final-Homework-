package com.biletx.response;

import com.biletx.enums.GenderType;
import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class BasketResponse {
    private Integer userId;

    private String vehicleNo;
    private VehicleStatus status;
    private VehicleType vehicleType;
    private String fromWhere;
    private String whereTo;

    private Date departureTime;
    private String departureClock;
    private String clockOfArrival;

    private String passengerName;
    private GenderType gender;

    private Integer price;

    public BasketResponse() {
        super();
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureClock() {
        return departureClock;
    }

    public void setDepartureClock(String departureClock) {
        this.departureClock = departureClock;
    }

    public String getClockOfArrival() {
        return clockOfArrival;
    }

    public void setClockOfArrival(String clockOfArrival) {
        this.clockOfArrival = clockOfArrival;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "BasketResponse{" +
                "userId=" + userId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", status=" + status +
                ", vehicleType=" + vehicleType +
                ", fromWhere='" + fromWhere + '\'' +
                ", whereTo='" + whereTo + '\'' +
                ", departureTime=" + departureTime +
                ", departureClock='" + departureClock + '\'' +
                ", clockOfArrival='" + clockOfArrival + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
