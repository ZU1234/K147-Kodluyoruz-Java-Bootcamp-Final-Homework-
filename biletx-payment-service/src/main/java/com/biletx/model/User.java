package com.biletx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.biletx.enums.UserType;

import javax.persistence.*;

@Entity

@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail", unique = true)
    private String mail;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;
    @Column(name = "phone")
    private String phone;


    public User() {
        super();
    }

    public User(String name, String mail, String password, UserType type, String phone) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.type = type;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                '}';
    }
}
