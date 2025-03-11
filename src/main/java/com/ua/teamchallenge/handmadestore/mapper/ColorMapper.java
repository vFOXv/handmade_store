package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.ColorDto;
import com.ua.teamchallenge.handmadestore.model.Color;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface ColorMapper {
    ColorDto toColorDto(Color color);

    Color toColor(ColorDto colorDto);
}
