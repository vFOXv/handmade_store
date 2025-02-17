package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.exception.TokenAlreadyConfirmedException;
import com.ua.teamchallenge.handmadestore.exception.TokenExpiredException;
import com.ua.teamchallenge.handmadestore.model.ConfirmationToken;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.ConfirmationTokenRepository;
import com.ua.teamchallenge.handmadestore.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.*;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Value("${email.confirmation.token.lifetime}")
    private Duration tokenLifetime;

    @Override
    public String saveConfirmationToken(ConfirmationToken token) {
        return confirmationTokenRepository.save(token).getToken();
    }

    @Override
    public ConfirmationToken getConfirmationToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new NoSuchElementException(TOKEN_NOT_FOUND));
    }

    @Override
    public ConfirmationToken generateConfirmationToken(User user) {
        return ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(tokenLifetime.toMinutes()))
                .user(user)
                .build();
    }

    @Override
    public void validateConfirmationToken(ConfirmationToken confirmationToken) {
        if (confirmationToken.getConfirmedAt() != null) {
            throw new TokenAlreadyConfirmedException(String.format(EMAIL_ALREADY_CONFIRMED,
                    confirmationToken.getUser().getEmail()));
        }

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException(TOKEN_EXPIRED);
        }
    }
}
