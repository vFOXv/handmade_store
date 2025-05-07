package com.ua.teamchallenge.handmadestore.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryFilterDto {
    private Long id;
    private String categoryName;
    private List<SubcategoryDto> subcategories;
}
