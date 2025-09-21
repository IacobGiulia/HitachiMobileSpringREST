package com.example.hitachimobilespringrest.service;

import com.example.hitachimobilespringrest.model.AppUser;

public interface UserService {

    AppUser registerUser(String username, String password);

    String loginUser(String username, String password);
}
