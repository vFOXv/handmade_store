package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapperConfig;
import com.ua.teamchallenge.handmadestore.dto.StyleDto;
import com.ua.teamchallenge.handmadestore.model.Style;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface StyleMapper {

    StyleDto toStyleDto(Style style);
    Style toStyle(StyleDto styleDto);

}
