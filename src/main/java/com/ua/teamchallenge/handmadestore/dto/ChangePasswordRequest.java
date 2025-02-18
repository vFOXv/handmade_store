package com.ua.teamchallenge.handmadestore.dto;

import com.ua.teamchallenge.handmadestore.dto.group.OnChangePassword;
import com.ua.teamchallenge.handmadestore.dto.validator.FieldMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.*;
import static com.ua.teamchallenge.handmadestore.util.ValidationRegExp.PASSWORDS_REGEXP;

@Data
@FieldMatch(first = "newPassword", second = "repeatNewPassword", message = PASSWORDS_NOT_MATCH)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto for change and reset user password")
public class ChangePasswordRequest {

    @NotBlank(groups = {OnChangePassword.class}, message = CURRENT_PASSWORD_REQUIRED)
    @Schema(description = "User current password", example = "Qwerty123")
    private String oldPassword;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Pattern(regexp = PASSWORDS_REGEXP, message = PASSWORDS_VALIDATION_MESSAGE)
    @Schema(description = "User new password", example = "Qwerty123")
    private String newPassword;

    @NotBlank(message = REPEAT_PASSWORD_REQUIRED)
    @Schema(description = "Repeat new password", example = "Qwerty123")
    private String repeatNewPassword;
}
