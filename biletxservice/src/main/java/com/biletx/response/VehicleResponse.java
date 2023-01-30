package com.biletx.response;

import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;

import java.util.Date;

public class VehicleResponse {
    private int id;
    private VehicleStatus status;

    private String no;

    private VehicleType vehicleType;

    private String fromWhere;

    private String whereTo;

    private Integer ridership;

    private String departureTime;

    private String departureClock;

    private String clockOfArrival;

    private Integer emptySeat;

    private Integer price;

    public VehicleResponse() {
        super();
    }

    public VehicleResponse(int id, VehicleStatus status, String no, VehicleType vehicleType, String fromWhere,
                           String whereTo, Integer ridership, String departureTime, String departureClock,
                           String clockOfArrival, Integer emptySeat, Integer price) {
        this.id = id;
        this.status = status;
        this.no = no;
        this.vehicleType = vehicleType;
        this.fromWhere = fromWhere;
        this.whereTo = whereTo;
        this.ridership = ridership;
        this.departureTime = departureTime;
        this.departureClock = departureClock;
        this.clockOfArrival = clockOfArrival;
        this.emptySeat = emptySeat;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public Integer getRidership() {
        return ridership;
    }

    public void setRidership(Integer ridership) {
        this.ridership = ridership;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
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

    public Integer getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(Integer emptySeat) {
        this.emptySeat = emptySeat;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "VehicleResponse{" +
                "status=" + status +
                ", no='" + no + '\'' +
                ", vehicleType=" + vehicleType +
                ", fromWhere='" + fromWhere + '\'' +
                ", whereTo='" + whereTo + '\'' +
                ", ridership=" + ridership +
                ", departureTime=" + departureTime +
                ", departureClock='" + departureClock + '\'' +
                ", clockOfArrival='" + clockOfArrival + '\'' +
                ", emptySeat=" + emptySeat +
                ", price=" + price +
                '}';
    }
}
