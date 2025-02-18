package com.ua.teamchallenge.handmadestore.service;

public interface AsyncEmailService {
    void sendEmail(String to, String email, String subject);
}
