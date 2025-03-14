package com.ua.teamchallenge.handmadestore.dto;

import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.Material;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private Category category;
    private Material material;
    private Double price;
    private int discount;
    private List<ColorDto> colors;
}
