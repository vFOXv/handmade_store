package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(config = MapStructConfig.class)
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, MaterialMapper.class, ColorMapper.class})
public interface ItemMapper {
    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "materialDto", source = "material")
    @Mapping(target = "colors", source = "colors")
    ItemDto toItemDto(Item item);

    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "material", source = "materialDto")
    @Mapping(target = "colors", source = "colors")
    Item toItem(ItemDto itemDto);
}
