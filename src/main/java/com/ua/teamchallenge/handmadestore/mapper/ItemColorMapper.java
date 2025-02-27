package com.ua.teamchallenge.handmadestore.mapper;


import com.ua.teamchallenge.handmadestore.dto.ItemColorDto;
import com.ua.teamchallenge.handmadestore.model.ItemColor;
import com.ua.teamchallenge.handmadestore.repository.ColorRepository;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {ItemRepository.class, ColorRepository.class})
public interface ItemColorMapper {

    ItemColorDto toItemColorDto(ItemColor itemColor);

    ItemColor toItemColor(ItemColorDto itemColorDto);

    List<ItemColorDto> toItemColorDtoList(List<ItemColor> itemColors);

}
