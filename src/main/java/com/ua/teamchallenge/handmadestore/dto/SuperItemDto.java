package com.ua.teamchallenge.handmadestore.dto;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class SuperItemDto {
    private Long id;
    private String name;
    private CategoryDto categoryDto;
    private Long idItemMinPrice;
    private List<ItemDto> items = new ArrayList<>();

}
