package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.SuperItemDto;
import com.ua.teamchallenge.handmadestore.model.SuperItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapStructConfig.class, uses = {CategoryMapper.class, ItemMapper.class})
public interface SuperItemMapper {
    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "items", target = "items")
    SuperItemDto toSuperItemDto(SuperItem superItem);
    SuperItem toSuperItem(SuperItemDto superItemDto);
}
