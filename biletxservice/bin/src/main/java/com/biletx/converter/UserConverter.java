package com.biletx.converter;

import com.biletx.model.User;
import com.biletx.request.UserRequest;
import com.biletx.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        response.setMail(user.getEmail());
        response.setName(user.getName());
        response.setType(user.getType());

        return response;
    }

    public User convert(UserRequest user,String hash) {
        User response = new User();
        response.setEmail(user.getMail());
        response.setName(user.getName());
        response.setType(user.getType());
        response.setPassword(hash);
        response.setPhone(user.getPhone());
        return response;
    }

    public List<UserResponse> convert(List<User> userList) {
        List<UserResponse> responseList = new ArrayList<>();
        userList.forEach(user -> responseList.add(convert(user)));
        return responseList;
    }

    public User convert(UserRequest userRequest) {
        User response =new User();
        response.setEmail(userRequest.getMail());
        response.setName(userRequest.getName());
        response.setPassword(userRequest.getPassword());
        response.setType(userRequest.getType());
        response.setPhone(userRequest.getPhone());
        return response;
    }
}
