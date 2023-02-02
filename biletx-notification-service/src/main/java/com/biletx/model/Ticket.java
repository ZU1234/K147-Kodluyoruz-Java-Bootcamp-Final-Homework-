package com.biletx.model;


import com.biletx.enums.GenderType;


public class Ticket {
    private Integer id;
    private User user;
    private Vehicle vehicle;

    private String passengerName;

    private GenderType gender;

    public Ticket() {
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", vehicleId=" + vehicle.getId() +
                ", passengerName='" + passengerName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
