package com.ua.teamchallenge.handmadestore.service.impl;


import com.ua.teamchallenge.handmadestore.exception.RefreshTokenExpiredException;
import com.ua.teamchallenge.handmadestore.model.RefreshToken;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.RefreshTokenRepository;
import com.ua.teamchallenge.handmadestore.repository.UserRepository;
import com.ua.teamchallenge.handmadestore.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.REFRESH_TOKEN_EXPIRED;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.refresh-expiration}")
    private Duration refreshExpiration;

    @Override
    public Optional<RefreshToken> getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public RefreshToken generateRefreshToken(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username).orElseThrow();
        deleteRefreshTokenByUser(user);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusHours(refreshExpiration.toHours()))
                .user(user)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpirationDate(RefreshToken token) {
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpiredException(REFRESH_TOKEN_EXPIRED);
        }
        return token;
    }

    @Override
    public void deleteRefreshTokenByUser(User user) {
        if (refreshTokenRepository.existsByUser(user)) {
            refreshTokenRepository.deleteByUser(user);
        }
    }
}
