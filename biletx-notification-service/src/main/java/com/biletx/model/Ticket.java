package com.biletx.model;


import com.biletx.enums.GenderType;

import javax.persistence.*;


@Entity
@Table(name = "tickets")

public class Ticket {

    @Id
    @SequenceGenerator(name = "ticket", sequenceName = "ticket_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
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
