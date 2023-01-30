package com.biletx.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_send_sms")
public class UserSendSms {
    @Column(name = "date")
    LocalDateTime sendDate;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "message")
    private String message;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    public UserSendSms() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "UserSendSms{" +
                "date=" + sendDate +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", user=" + user +
                ", vehicle=" + vehicle +
                '}';
    }
}

