package com.biletx.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.biletx.enums.GenderType;

import javax.persistence.*;


@Entity
@Table(name = "boxes")

public class Basket {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "box", sequenceName = "box_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "box_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleId", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vehicle vehicle;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderType gender;


    public Basket() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "Basket{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", userId=" + userId +
                ", passengerName='" + passengerName + '\'' +
                ", gender=" + gender +
                '}';
    }
}

