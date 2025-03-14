package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/r1")
public class TestController {

    private final ItemRepository itemRepository;

    public TestController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public List<Item> allItems(){
        return itemRepository.findAll();
    }
}
