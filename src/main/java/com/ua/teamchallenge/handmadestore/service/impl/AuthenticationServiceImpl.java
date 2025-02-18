package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.AuthenticationRequest;
import com.ua.teamchallenge.handmadestore.dto.AuthenticationResponse;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenRequest;
import com.ua.teamchallenge.handmadestore.dto.RefreshTokenResponse;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.model.RefreshToken;
import com.ua.teamchallenge.handmadestore.model.ResetPasswordToken;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.UserRepository;
import com.ua.teamchallenge.handmadestore.security.JwtService;
import com.ua.teamchallenge.handmadestore.service.AuthenticationService;
import com.ua.teamchallenge.handmadestore.service.EmailSenderService;
import com.ua.teamchallenge.handmadestore.service.RefreshTokenService;
import com.ua.teamchallenge.handmadestore.service.ResetPasswordTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.REFRESH_TOKEN_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final EmailSenderService emailSenderService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Authenticating user with username: '{}'", request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));

        log.info("User '{}' successfully authenticated", request.getUsername());
        return new AuthenticationResponse(jwtService.generateToken(request.getUsername()),
                refreshTokenService.generateRefreshToken(request.getUsername()).getToken());
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        return refreshTokenService.getRefreshToken(request.refreshToken())
                .map(refreshTokenService::verifyExpirationDate)
                .map(RefreshToken::getUser)
                .map(user -> new RefreshTokenResponse(jwtService.generateToken(user.getUsername()),
                        request.refreshToken()))
                .orElseThrow(() -> new EntityNotFoundException(REFRESH_TOKEN_NOT_FOUND));
    }

    @Override
    @Transactional
    public void resetPassword(String newPassword, String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.getResetPasswordToken(token);
        resetPasswordTokenService.validateResetPasswordToken(resetPasswordToken);

        resetPasswordToken.setConfirmedAt(LocalDateTime.now());
        resetPasswordTokenService.saveResetPasswordToken(resetPasswordToken);

        User user = resetPasswordToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
