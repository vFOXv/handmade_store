package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.model.ConfirmationToken;
import com.ua.teamchallenge.handmadestore.model.User;

public interface ConfirmationTokenService {
    String saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getConfirmationToken(String token);

    ConfirmationToken generateConfirmationToken(User user);

    void validateConfirmationToken(ConfirmationToken confirmationToken);
}
