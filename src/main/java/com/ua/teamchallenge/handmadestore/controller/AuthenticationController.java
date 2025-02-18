package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.controller.openapi.AuthenticationControllerOpenApi;
import com.ua.teamchallenge.handmadestore.dto.*;
import com.ua.teamchallenge.handmadestore.service.AuthenticationService;
import com.ua.teamchallenge.handmadestore.service.EmailSenderService;
import com.ua.teamchallenge.handmadestore.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-resource-path}/auth")
public class AuthenticationController implements AuthenticationControllerOpenApi {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final EmailSenderService emailSenderService;

    @Value("${api.frontend-base-url}")
    private String apiFrontendBaseUrl;

    private static final String TOKEN_RESET_PASSWORD_URL = "/auth/reset-pass?token=";

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody RegistrationRequestDto request) {
        return registrationService.register(request);
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
    }

    @PostMapping("/resend-email-confirmation")
    @ResponseStatus(HttpStatus.OK)
    public void resendConfirmationEmail(@Valid @RequestBody EmailRequestDto emailRequestDto) {
        emailSenderService.sendConfirmationEmail(emailRequestDto.getEmail());
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return authenticationService.refreshToken(request);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void sendResetPasswordEmail(@Valid @RequestBody EmailRequestDto emailRequestDto) {
        emailSenderService.sendResetPasswordEmail(emailRequestDto.getEmail());
    }

    @GetMapping("/reset-password")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Void> redirectToResetPasswordPage(@RequestParam("token") String token) {
        HttpHeaders headers = new HttpHeaders();
        //todo add page for set new password
        //headers.add("Location", apiFrontendBaseUrl + TOKEN_RESET_PASSWORD_URL + token);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PatchMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ChangePasswordRequest request,
                              @RequestParam("token") String token) {
        authenticationService.resetPassword(request.getNewPassword(), token);
    }
}
