package com.ua.teamchallenge.handmadestore.service.impl;


import com.ua.teamchallenge.handmadestore.dto.CategoryFilterDto;
import com.ua.teamchallenge.handmadestore.dto.SubcategoryDto;
import com.ua.teamchallenge.handmadestore.mapper.*;
import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.Filter;
import com.ua.teamchallenge.handmadestore.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FilterService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final MaterialRepository materialRepository;
    private final ColorRepository colorRepository;
    private final CategoryMapper categoryMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final MaterialMapper materialMapper;
    private final ColorMapper colorMapper;
    private final CategoryFilterMapper categoryFilterMapper;

    @Autowired
    private Filter filter;

    public Filter getFilters() {

        // Получаем категории и заполняем их подкатегории
        // Получаем категории и группируем их по categoryName
        Map<String, List<Category>> categoriesByName = categoryRepository.findAll().stream()
                .collect(Collectors.groupingBy(Category::getCategoryName));

        // Создаём CategoryFilterDto для каждой уникальной категории
        List<CategoryFilterDto> categoryDtos = categoriesByName.entrySet().stream()
                .map(entry -> {
                    String categoryName = entry.getKey();
                    List<Category> categories = entry.getValue();

                    // Собираем все подкатегории для данной категории
                    List<SubcategoryDto> subcategoryDtos = categories.stream()
                            .flatMap(category -> subcategoryRepository.findAllByCategoryId(category.getId()).stream())
                            .distinct() // Убираем дубликаты подкатегорий
                            .map(subcategoryMapper::toSubcategoryDto)
                            .collect(Collectors.toList());

                    // Создаём DTO для категории
                    CategoryFilterDto dto = new CategoryFilterDto();
                    dto.setCategoryName(categoryName);
                    // Устанавливаем id первой категории (или можно оставить null, если id не нужен)
                    dto.setId(categories.get(0).getId());
                    dto.setSubcategories(subcategoryDtos);

                    return dto;
                })
                .collect(Collectors.toList());

        filter.setCategories(categoryDtos);
        filter.setMaterials(materialRepository.findAll().stream().map(materialMapper::toMaterialDto).collect(Collectors.toList()));
        filter.setColors(colorRepository.findAll().stream().map(colorMapper::toColorDto).collect(Collectors.toList()));
        filter.setMinPrice(itemRepository.findMinPrice());
        filter.setMaxPrice(itemRepository.findMaxPrice());
        return filter;
    }
}
