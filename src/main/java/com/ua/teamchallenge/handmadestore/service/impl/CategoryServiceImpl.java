package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.CategoryDto;
import com.ua.teamchallenge.handmadestore.mapper.CategoryMapper;
import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.repository.CategoryRepository;
import com.ua.teamchallenge.handmadestore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public List<CategoryDto> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDto = new ArrayList<>();
        for(Category item : categories){
            categoryDto.add(categoryMapper.toCategoryDto(item));
        }
        return categoryDto;
    }

    public CategoryDto getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElse(null);
        return categoryMapper.toCategoryDto(category);
    }
}
