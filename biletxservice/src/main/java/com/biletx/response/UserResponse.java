package com.biletx.response;

import com.biletx.enums.UserType;

public class UserResponse {
    private String name;
    private String email;
    private UserType type;


    public UserResponse() {
        super();
    }

    public UserResponse(String name, String email, UserType type) {
        this.name = name;
        this.email = email;
        this.type = type;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
