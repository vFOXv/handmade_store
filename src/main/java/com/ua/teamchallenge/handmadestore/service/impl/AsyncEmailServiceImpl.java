package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.exception.FailedToSendEmailException;
import com.ua.teamchallenge.handmadestore.service.AsyncEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.FAILED_TO_SEND_EMAIL;
import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.UTF8;

@Service
@RequiredArgsConstructor
public class AsyncEmailServiceImpl implements AsyncEmailService {
    private final JavaMailSender mailSender;
    @Value("${email.sender.login}")
    private String sender;

    @Override
    @Async("myAsyncPoolTaskExecutor")
    public void sendEmail(String to, String email, String subject) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF8);
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(sender);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new FailedToSendEmailException(FAILED_TO_SEND_EMAIL);
        }
    }
}
