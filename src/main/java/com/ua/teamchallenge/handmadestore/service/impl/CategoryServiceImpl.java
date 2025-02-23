package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.CategoryDto;

import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.CategoryMapper;
import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.repository.CategoryRepository;
import com.ua.teamchallenge.handmadestore.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.CATEGORY_NOT_FOUND_BY_ID;



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
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CATEGORY_NOT_FOUND_BY_ID + id)));
        return categoryMapper.toCategoryDto(category);
    }
}
