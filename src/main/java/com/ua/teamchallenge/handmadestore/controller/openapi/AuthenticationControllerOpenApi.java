package com.ua.teamchallenge.handmadestore.controller.openapi;

import com.ua.teamchallenge.handmadestore.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Authentication Controller", description = "API to work with authentication")
public interface AuthenticationControllerOpenApi {
    @Operation(summary = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully authenticated",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Missing required parameters"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Wrong credentials"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected internal error")
    })
    AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request);

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User successfully registered, confirmation email sent",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Not valid user data"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Username or email already exists"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected internal error")
    })
    UserDto register(@Valid @RequestBody RegistrationRequestDto request);

    @Operation(summary = "Email Verification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email successfully confirmed"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Token has expired"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Token not found"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email is already verified"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected internal error")
    })
    void confirm(@RequestParam("token") String token);

    @Operation(summary = "Resend message for email verification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email sent successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to send email message"),
    })
    void resendConfirmationEmail(@RequestBody EmailRequestDto request);

    @Operation(summary = "Get new access token by refresh token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "New access token successfully generated",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RefreshTokenResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Missing required parameters"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Token has expired"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Token not found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected internal error")
    })
    RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request);

    @Operation(summary = "Send reset password email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully sent reset password email"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to send email message")
    })
    void sendResetPasswordEmail(@Valid @RequestBody EmailRequestDto emailRequestDto);

    @Operation(summary = "Redirect to reset password page")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successfully redirected to reset password page"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to send email message")
    })
    ResponseEntity<Void> redirectToResetPasswordPage(@RequestParam("token") String token);

    @Operation(summary = "Reset user password and set new one")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password successfully changed"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Missing required parameters"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Token has expired"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Token not found"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Token has already been used"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected internal error")
    })
    void resetPassword(@Valid @RequestBody ChangePasswordRequest request,
                       @RequestParam("token") String token);
}
