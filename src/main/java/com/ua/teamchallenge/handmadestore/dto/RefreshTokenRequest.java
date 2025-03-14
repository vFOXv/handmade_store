package com.ua.teamchallenge.handmadestore.dto;

import jakarta.validation.constraints.NotBlank;

import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.REFRESH_TOKEN_REQUIRED;

public record RefreshTokenRequest(@NotBlank(message = REFRESH_TOKEN_REQUIRED) String refreshToken) {
}
