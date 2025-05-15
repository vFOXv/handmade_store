package com.ua.teamchallenge.handmadestore.dto;

import com.ua.teamchallenge.handmadestore.model.Item;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class SuperItemDto {
    private Long id;
    private String name;
    private CategoryDto categoryDto;
    private List<Item> items = new ArrayList<>();
}
