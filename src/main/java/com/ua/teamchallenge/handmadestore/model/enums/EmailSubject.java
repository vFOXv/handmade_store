package com.ua.teamchallenge.handmadestore.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailSubject {
    CONFIRM_EMAIL("Email confirmation",
            "emails/confirm-email",
            "${api.backend-base-url}/${api.base-resource-path}/auth/confirm?token="),

    RESET_PASSWORD("Reset password instruction",
            "emails/reset-password-email",
            "${api.backend-base-url}/${api.base-resource-path}/auth/reset-password?token=");

    private final String subject;
    private final String template;
    private final String url;
}