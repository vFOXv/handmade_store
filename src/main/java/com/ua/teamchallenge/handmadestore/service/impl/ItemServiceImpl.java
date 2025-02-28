package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.ITEM_NOT_FOUND_BY_ID;

@Service
public class ItemServiceImpl {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> findAll(){
        List<Item> items = itemRepository.findAllItems();

        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item : items){
            itemsDto.add(itemMapper.toItemDto(item));
        }
        return itemsDto;
    }

    public ItemDto findById(long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ITEM_NOT_FOUND_BY_ID + id)));
        return itemMapper.toItemDto(item);
    }
}
