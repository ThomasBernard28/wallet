package com.example.Api.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUsers(){
        return List.of(
                new User(
                        "thomasbernard02032811708",
                        "02032817708",
                        "1234",
                        "Bernard",
                        "Thomas",
                        "FR"
                )
        );
    }
}
