package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.exception.TokenAlreadyConfirmedException;
import com.ua.teamchallenge.handmadestore.mapper.UserMapper;
import com.ua.teamchallenge.handmadestore.model.ConfirmationToken;
import com.ua.teamchallenge.handmadestore.model.ResetPasswordToken;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.model.enums.EmailSubject;
import com.ua.teamchallenge.handmadestore.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.EMAIL_ALREADY_CONFIRMED;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AsyncEmailService asyncEmailService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TemplateEngine htmlTemplateEngine;

    @Value("${api.backend-base-url}")
    private String apiBackendBaseUrl;

    @Value("${api.base-resource-path}")
    private String apiBaseResourcePath;

    @Override
    public void sendConfirmationEmail(String email) {
        User user = userMapper.toUser(userService.getUserByEmail(email));

        if (user.isEnabled()) {
            throw new TokenAlreadyConfirmedException(String.format(EMAIL_ALREADY_CONFIRMED, email.toLowerCase()));
        }

        ConfirmationToken confirmationToken = confirmationTokenService.generateConfirmationToken(user);
        String token = confirmationTokenService.saveConfirmationToken(confirmationToken);

        String emailContent = buildEmailContent(user.getUsername(), token, EmailSubject.CONFIRM_EMAIL);
        asyncEmailService.sendEmail(email.toLowerCase(), emailContent,
                EmailSubject.CONFIRM_EMAIL.getSubject());
    }

    @Override
    public void sendResetPasswordEmail(String email) {
        User user = userMapper.toUser(userService.getUserByEmail(email));

        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.generateResetPasswordToken(user);
        String token = resetPasswordTokenService.saveResetPasswordToken(resetPasswordToken);

        String emailContent = buildEmailContent(user.getUsername(), token, EmailSubject.RESET_PASSWORD);
        asyncEmailService.sendEmail(email.toLowerCase(), emailContent,
                EmailSubject.RESET_PASSWORD.getSubject());
    }

    private String buildEmailContent(String username, String token, EmailSubject subject) {
        Context ctx = new Context();
        ctx.setVariable("username", username);
        ctx.setVariable("url", apiBackendBaseUrl + apiBaseResourcePath + subject.getPath() + token);

        return htmlTemplateEngine.process(subject.getTemplate(), ctx);
    }
}
