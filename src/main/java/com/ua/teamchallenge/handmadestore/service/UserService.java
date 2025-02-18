package com.ua.teamchallenge.handmadestore.service;

import com.ua.teamchallenge.handmadestore.dto.UserDto;
import com.ua.teamchallenge.handmadestore.model.User;

public interface UserService {
    UserDto getUserByEmail(String email);

    void enableUser(User user);
}
