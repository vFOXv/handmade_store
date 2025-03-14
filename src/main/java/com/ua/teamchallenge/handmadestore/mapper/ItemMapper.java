package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.model.Item;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface ItemMapper {
    ItemDto toItemDto(Item item);

    Item toItem(ItemDto itemDto);
}
