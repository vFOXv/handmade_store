package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.RegistrationRequestDto;
import com.ua.teamchallenge.handmadestore.dto.UserDto;
import com.ua.teamchallenge.handmadestore.exception.EntityAlreadyExistsException;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.UserMapper;
import com.ua.teamchallenge.handmadestore.model.ConfirmationToken;
import com.ua.teamchallenge.handmadestore.model.Role;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.RoleRepository;
import com.ua.teamchallenge.handmadestore.repository.UserRepository;
import com.ua.teamchallenge.handmadestore.service.ConfirmationTokenService;
import com.ua.teamchallenge.handmadestore.service.EmailSenderService;
import com.ua.teamchallenge.handmadestore.service.RegistrationService;
import com.ua.teamchallenge.handmadestore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    @Override
    @Transactional
    public UserDto register(RegistrationRequestDto request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_USERNAME, request.getUsername()));
        }
        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_EMAIL, request.getEmail()));
        }

        Role defaultRole = roleRepository.findByRoleName(ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ROLE_NOT_FOUND, ROLE_USER)));
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .isEnabled(false)
                .build();

        User savedUser = userRepository.save(user);
        emailSenderService.sendConfirmationEmail(savedUser.getEmail());
        return userMapper.toUserDto(savedUser);
    }

    @Override
    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);
        confirmationTokenService.validateConfirmationToken(confirmationToken);

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        userService.enableUser(confirmationToken.getUser());
    }
}
