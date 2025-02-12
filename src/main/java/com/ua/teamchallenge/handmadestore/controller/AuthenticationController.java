package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.controller.openapi.AuthenticationControllerOpenApi;
import com.ua.teamchallenge.handmadestore.dto.AuthenticationRequest;
import com.ua.teamchallenge.handmadestore.dto.AuthenticationResponse;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenRequest;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenResponse;
import com.ua.teamchallenge.handmadestore.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-resource-path}/auth")
public class AuthenticationController implements AuthenticationControllerOpenApi {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return authenticationService.refreshToken(request);
    }
}
