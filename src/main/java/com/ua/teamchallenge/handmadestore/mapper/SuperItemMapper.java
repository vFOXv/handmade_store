package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.SuperItemDto;
import com.ua.teamchallenge.handmadestore.model.SuperItem;
import org.mapstruct.Mapper;


@Mapper(config = MapStructConfig.class)
public interface SuperItemMapper {
    SuperItemDto toSuperItemDto(SuperItem superItem);
    SuperItem toSuperItem(SuperItemDto superItemDto);
}
