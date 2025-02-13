package com.ua.teamchallenge.handmadestore.dto;

import com.ua.teamchallenge.handmadestore.dto.validator.FieldMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.*;
import static com.ua.teamchallenge.handmadestore.util.ValidationRegExp.*;

@Data
@FieldMatch(first = "password", second = "repeatPassword", message = PASSWORDS_NOT_MATCH)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto for registration new user")
public class RegistrationRequestDto {

    @NotBlank(message = USERNAME_REQUIRED)
    @Pattern(regexp = USERNAME_REGEXP, message = USERNAME_VALIDATION_MESSAGE)
    @Schema(description = "Username", example = "User1")
    private String username;

    @NotBlank(message = EMAIL_REQUIRED)
    @Pattern(regexp = EMAIL_REGEXP, message = EMAIL_VALIDATION_MESSAGE)
    @Schema(description = "User email", example = "johndoe@gmail.com")
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Pattern(regexp = PASSWORDS_REGEXP, message = PASSWORDS_VALIDATION_MESSAGE)
    @Schema(description = "User Password", example = "Qwerty123")
    private String password;

    @NotBlank(message = REPEAT_PASSWORD_REQUIRED)
    @Schema(description = "Repeat Password", example = "Qwerty123")
    private String repeatPassword;
}