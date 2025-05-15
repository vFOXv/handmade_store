package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/set")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowCredentials = "true")
public class ItemSetController {

    private final ItemServiceImpl itemService;
    private final ItemMapper itemMapper;

    @PostMapping("/new_item")
    public ItemDto saveItem(@RequestBody Item item) {
        item.getImages().forEach(image -> image.setItem(item));
        item.setCreatedAt(LocalDate.now());
        itemService.saveItem(item);
        return itemMapper.toItemDto(item);
    }
}




