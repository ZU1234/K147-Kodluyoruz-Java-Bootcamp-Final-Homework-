package com.biletx.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_send_mails")
public class UserSendMail {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "message")
    private String message;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", sendDate=" + sendDate +
                '}';
    }
}
