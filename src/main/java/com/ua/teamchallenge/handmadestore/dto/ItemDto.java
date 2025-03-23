package com.ua.teamchallenge.handmadestore.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private CategoryDto categoryDto;
    private MaterialDto materialDto;
    private BigDecimal price;
    private int quantity;
    private int discount;
    private List<ColorDto> colors;
    private List<ImageDto> images;
}
