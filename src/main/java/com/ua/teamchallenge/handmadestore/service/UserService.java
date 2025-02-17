package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
