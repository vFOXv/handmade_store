package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.model.ResetPasswordToken;
import com.ua.teamchallenge.handmadestore.model.User;

public interface ResetPasswordTokenService {
    ResetPasswordToken generateResetPasswordToken(User user);

    String saveResetPasswordToken(ResetPasswordToken token);

    ResetPasswordToken getResetPasswordToken(String token);

    void validateResetPasswordToken(ResetPasswordToken resetPasswordToken);
}
