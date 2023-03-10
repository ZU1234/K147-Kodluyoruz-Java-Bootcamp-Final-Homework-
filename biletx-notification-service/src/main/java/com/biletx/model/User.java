package com.biletx.model;


import com.biletx.enums.UserType;


public class User {


    private Integer id;

    private String name;

    private String email;

    private String password;

    private UserType type;

    private String phone;


    public User() {
        super();
    }

    public User(String name, String email, String password, UserType type, String phone) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", mail='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                '}';
    }
}
