package com.ua.teamchallenge.handmadestore.mapper;


import com.ua.teamchallenge.handmadestore.dto.CategoryFilterDto;
import com.ua.teamchallenge.handmadestore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubcategoryMapper.class})
public interface CategoryFilterMapper {

    @Mapping(target = "categoryName", source = "categoryName")
    CategoryFilterDto toCategoryDto(Category category);
    @Mapping(target = "categoryName", source = "categoryName")
    Category toCategory(CategoryFilterDto categoryFilterDto);

}
