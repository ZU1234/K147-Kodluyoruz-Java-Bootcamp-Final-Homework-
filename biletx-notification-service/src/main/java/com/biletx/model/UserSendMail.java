package com.biletx.model;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_send_mails")
public class UserSendMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "message")
    private String message;

    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "date")
    LocalDateTime sendDate;


    public UserSendMail() {
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "UserSendMail{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
