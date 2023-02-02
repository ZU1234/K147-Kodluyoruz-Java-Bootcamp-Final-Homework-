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

    @Column(name = "user_id")
    private Integer userId;
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


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
                "sendDate=" + sendDate +
                ", id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", vehicle=" + vehicle +
                '}';
    }
}

