package com.ua.teamchallenge.handmadestore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.PASSWORD_REQUIRED;
import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.USERNAME_REQUIRED;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto for login user")
public class AuthenticationRequest {
    @NotBlank(message = USERNAME_REQUIRED)
    @Schema(description = "Username", example = "User")
    private String username;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Schema(description = "User password", example = "Qwerty123")
    private String password;
}
