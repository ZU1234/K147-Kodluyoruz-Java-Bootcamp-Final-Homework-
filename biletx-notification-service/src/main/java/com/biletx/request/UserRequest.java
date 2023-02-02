package com.biletx.request;

import com.biletx.enums.UserType;

public class UserRequest {

    private String name;
    private String email;
    private String password;
    private UserType type;
    private String phone;

    public UserRequest() {
        super();
    }

    public UserRequest(String name, String email, String password, UserType type, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;
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
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                '}';
    }
}
