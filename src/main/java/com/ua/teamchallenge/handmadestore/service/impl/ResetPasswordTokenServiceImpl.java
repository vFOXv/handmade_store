package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.exception.TokenAlreadyConfirmedException;
import com.ua.teamchallenge.handmadestore.exception.TokenExpiredException;
import com.ua.teamchallenge.handmadestore.model.ResetPasswordToken;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.ResetPasswordTokenRepository;
import com.ua.teamchallenge.handmadestore.service.ResetPasswordTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.*;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    @Value("${reset.password.token.lifetime}")
    private Duration tokenLifetime;

    @Override
    public ResetPasswordToken generateResetPasswordToken(User user) {
        return ResetPasswordToken.builder()
                .token(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(tokenLifetime.toMinutes()))
                .user(user)
                .build();
    }

    @Override
    @Transactional
    public String saveResetPasswordToken(ResetPasswordToken token) {
        return resetPasswordTokenRepository.save(token).getToken();
    }

    @Override
    @Transactional(readOnly = true)
    public ResetPasswordToken getResetPasswordToken(String token) {
        return resetPasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException(TOKEN_NOT_FOUND));
    }

    @Override
    public void validateResetPasswordToken(ResetPasswordToken resetPasswordToken) {
        if (resetPasswordToken.getConfirmedAt() != null) {
            throw new TokenAlreadyConfirmedException(TOKEN_ALREADY_USED);
        }

        if (resetPasswordToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException(TOKEN_EXPIRED);
        }
    }
}
