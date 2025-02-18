package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.dto.AuthenticationRequest;
import com.ua.teamchallenge.handmadestore.dto.AuthenticationResponse;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenRequest;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void resetPassword(String newPassword, String token);
}
