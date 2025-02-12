package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.model.RefreshToken;
import com.ua.teamchallenge.handmadestore.model.User;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> getRefreshToken(String token);

    RefreshToken generateRefreshToken(String username);

    RefreshToken verifyExpirationDate(RefreshToken token);

    void deleteRefreshTokenByUser(User user);
}
