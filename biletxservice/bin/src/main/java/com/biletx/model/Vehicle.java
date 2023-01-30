package com.biletx.model;

import com.biletx.enums.VehicleStatus;
import com.biletx.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")

public class Vehicle {
    @Id

    @SequenceGenerator(name = "vehicle", sequenceName = "vehicle_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VehicleStatus status = VehicleStatus.ACTIVE;
    @Column(name = "no")
    private String no;
    @Column(name = "type")
    @Enumerated(EnumType.STRING )
    private VehicleType vehicleType;
    @Column(name = "from_where")
    private String fromWhere;
    @Column(name = "where_to")
    private String whereTo;
    @Column(name = "ridership")
    private Integer ridership;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "departure_clock")
    private String departureClock;

    @Column(name = "clock_arrival")
    private String clockOfArrival;

    @Column(name = "empty_seat")
    private Integer emptySeat;
    @Column(name = "price")
    private Integer price;

    public Vehicle() {
        super();
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRidership() {
        return ridership;
    }

    public void setRidership(Integer ridership) {
        this.ridership = ridership;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", status=" + status +
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
