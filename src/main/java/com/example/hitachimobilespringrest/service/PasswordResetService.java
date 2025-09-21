package com.example.hitachimobilespringrest.service;

import com.example.hitachimobilespringrest.model.AppUser;

public interface PasswordResetService {

    String createPasswordResetToken(String username);

    void confirmPasswordReset(String token, String newPassword);
}
