package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.UserDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.UserMapper;
import com.ua.teamchallenge.handmadestore.model.User;
import com.ua.teamchallenge.handmadestore.repository.UserRepository;
import com.ua.teamchallenge.handmadestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.USER_NOT_FOUND_BY_EMAIL;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL,
                        email.toLowerCase())));
        return userMapper.toUserDto(user);
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
