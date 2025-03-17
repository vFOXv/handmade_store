package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.CategoryDto;
import com.ua.teamchallenge.handmadestore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubcategoryMapper.class})
public interface CategoryMapper {
    @Mapping(target = "subcategoryDto", source = "subcategory")
    CategoryDto toCategoryDto(Category category);
    @Mapping(target = "subcategory", source = "subcategoryDto")
    Category toCategory(CategoryDto categoryDto);
}
