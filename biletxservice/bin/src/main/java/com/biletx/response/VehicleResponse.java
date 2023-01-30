package com.biletx.response;

import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;

import java.util.Date;

public class VehicleResponse {

    private VehicleStatus status;

    private String no;

    private VehicleType vehicleType;

    private String fromWhere;

    private String whereTo;

    private Integer ridership;

    private Date departureTime;

    private String departureClock;

    private String clockOfArrival;

    private Integer emptySeat;

    private Integer price;

    public VehicleResponse() {
      super();
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
