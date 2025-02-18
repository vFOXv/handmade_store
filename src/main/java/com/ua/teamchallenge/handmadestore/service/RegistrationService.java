package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.dto.RegistrationRequestDto;
import com.ua.teamchallenge.handmadestore.dto.UserDto;

public interface RegistrationService {
    UserDto register(RegistrationRequestDto request);

    void confirmToken(String token);

    void resendConfirmationEmail(String email);
}
