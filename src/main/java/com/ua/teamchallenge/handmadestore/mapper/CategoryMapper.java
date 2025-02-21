package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapperConfig;
import com.ua.teamchallenge.handmadestore.dto.CategoryDto;
import com.ua.teamchallenge.handmadestore.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);
}
