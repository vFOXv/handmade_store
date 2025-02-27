package com.ua.teamchallenge.handmadestore.dto;

import lombok.Data;

@Data
public class ItemColorDto {
    private Long id;
    private Long itemId;
    private Long colorId;
    private int quantity;
}
