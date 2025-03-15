package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.SubcategoryDto;
import com.ua.teamchallenge.handmadestore.model.Subcategory;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface SubcategoryMapper {
    SubcategoryDto toCategoryDto(Subcategory category);

    Subcategory toCategory(SubcategoryDto categoryDto);
}
