package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapperConfig;
import com.ua.teamchallenge.handmadestore.dto.UserDto;
import com.ua.teamchallenge.handmadestore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDTO);
}
