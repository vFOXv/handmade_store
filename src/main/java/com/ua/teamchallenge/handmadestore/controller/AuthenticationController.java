package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.controller.openapi.AuthenticationControllerOpenApi;
import com.ua.teamchallenge.handmadestore.dto.*;
import com.ua.teamchallenge.handmadestore.service.AuthenticationService;
import com.ua.teamchallenge.handmadestore.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-resource-path}/auth")
public class AuthenticationController implements AuthenticationControllerOpenApi {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

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
        registrationService.resendConfirmationEmail(emailRequestDto.getEmail());
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return authenticationService.refreshToken(request);
    }
}
