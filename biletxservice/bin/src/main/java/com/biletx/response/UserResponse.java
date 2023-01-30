package com.biletx.response;

import com.biletx.enums.UserType;

public class UserResponse {
    private String name;
    private String email;
    private UserType type;


    public UserResponse() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
