package com.ua.teamchallenge.handmadestore.exception;

public class FailedToSendEmailException extends RuntimeException {
    public FailedToSendEmailException(String message) {
        super(message);
    }
}
