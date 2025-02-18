package com.ua.teamchallenge.handmadestore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.EMAIL_REQUIRED;
import static com.ua.teamchallenge.handmadestore.util.ValidationConstants.EMAIL_VALIDATION_MESSAGE;
import static com.ua.teamchallenge.handmadestore.util.ValidationRegExp.EMAIL_REGEXP;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto for resend email confirmation")
public class EmailRequestDto {

    @NotBlank(message = EMAIL_REQUIRED)
    @Pattern(regexp = EMAIL_REGEXP, message = EMAIL_VALIDATION_MESSAGE)
    @Schema(description = "User email", example = "johndoe@gmail.com")
    private String email;
}
