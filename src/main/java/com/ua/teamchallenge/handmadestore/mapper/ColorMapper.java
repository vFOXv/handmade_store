package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapperConfig;
import com.ua.teamchallenge.handmadestore.dto.ColorDto;
import com.ua.teamchallenge.handmadestore.model.Color;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ColorMapper {

    ColorDto toColorDto(Color color);
    Color toColor(ColorDto colorDto);
}
