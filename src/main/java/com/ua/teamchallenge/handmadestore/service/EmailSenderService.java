package com.ua.teamchallenge.handmadestore.service;

public interface EmailSenderService {
    void sendConfirmationEmail(String email);
    void sendResetPasswordEmail(String username);
}
