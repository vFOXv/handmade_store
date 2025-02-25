package com.ua.teamchallenge.handmadestore.dto;

import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.Material;
import com.ua.teamchallenge.handmadestore.model.Style;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Category category;
    private Material material;
    private Style style;
    private Double price;
    private int discount;

}
