package com.ua.teamchallenge.handmadestore.util;

public class ValidationConstants {
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String REPEAT_PASSWORD_REQUIRED = "Repeat password is required";
    public static final String CURRENT_PASSWORD_REQUIRED = "Current password is required";
    public static final String REFRESH_TOKEN_REQUIRED = "Refresh token is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String PASSWORDS_NOT_MATCH = "Password and repeat password do not match";
    public static final String USERNAME_VALIDATION_MESSAGE = "Username must be from 2 to 30 characters long " +
            "and contains only  characters and numbers";
    public static final String EMAIL_VALIDATION_MESSAGE = "Not valid email";
    public static final String PASSWORDS_VALIDATION_MESSAGE = "Your password must contain upper and lower case " +
            "letters and numbers, at least 7 and maximum 30 characters.";
    
    private ValidationConstants() {
    }
}
