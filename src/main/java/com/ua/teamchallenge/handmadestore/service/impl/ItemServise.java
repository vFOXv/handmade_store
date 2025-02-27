package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.repository.ItemColorRepository;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.ITEM_NOT_FOUND_BY_ID;

@Service
public class ItemServise {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemColorRepository itemColorRepository;


    public ItemServise(ItemRepository itemRepository, ItemMapper itemMapper, ItemColorRepository itemColorRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.itemColorRepository = itemColorRepository;
    }

    public List<ItemDto> findAll(){
        List<Item> items = itemRepository.findAll();
        for(Item item : items){
            itemColorRepository.findByItemId(item.getId());
        }
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
