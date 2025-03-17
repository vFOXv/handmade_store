package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.SubcategoryDto;
import com.ua.teamchallenge.handmadestore.model.Subcategory;
import org.mapstruct.Mapper;

//@Mapper(config = MapStructConfig.class)
@Mapper(componentModel = "spring")
public interface SubcategoryMapper {
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);

    Subcategory toSubcategory(SubcategoryDto subcategoryDto);
}
