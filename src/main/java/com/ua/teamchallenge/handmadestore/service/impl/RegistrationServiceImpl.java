package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.RegistrationRequestDto;
import com.ua.teamchallenge.handmadestore.dto.UserDto;
import com.ua.teamchallenge.handmadestore.exception.EntityAlreadyExistsException;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.UserMapper;
import com.ua.teamchallenge.handmadestore.model.Role;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.RoleRepository;
import com.ua.teamchallenge.handmadestore.repository.UserRepository;
import com.ua.teamchallenge.handmadestore.service.RegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional
    public UserDto register(RegistrationRequestDto request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_USERNAME, request.getUsername()));
        }
        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_EMAIL, request.getEmail()));
        }

        Role roleUser = roleRepository.findByRoleName(ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ROLE_NOT_FOUND, ROLE_USER)));
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .isEnabled(false)
                .build();

        //  sendConfirmationEmail(request.getEmail());
        return userMapper.toUserDto(userRepository.save(user));
    }
}
