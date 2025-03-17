package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.dto.SubcategoryDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.mapper.SubcategoryMapper;
import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.model.Material;
import com.ua.teamchallenge.handmadestore.model.Subcategory;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import com.ua.teamchallenge.handmadestore.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.ITEM_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(itemMapper.toItemDto(item));
        }
        return itemsDto;
    }

    @Transactional(readOnly = true)
    public ItemDto findById(long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ITEM_NOT_FOUND_BY_ID, id)));
        return itemMapper.toItemDto(item);
    }

    public List<ItemDto> sortToCategory(long id) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();

        for (Item item : items) {
            if (id == item.getCategory().getId()) {
                itemsDto.add(itemMapper.toItemDto(item));
                System.out.println("1 -----> " + "idItem"+ item.getId() + "id"+ id);
            }
        }

        return itemsDto;
    }

    public List<ItemDto> sortToSubcategory(long id) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (id == item.getCategory().getSubcategory().getId()) {
                itemsDto.add(itemMapper.toItemDto(item));
            }
        }
        return itemsDto;
    }

    public List<ItemDto> sortToCategoryAndSubcategory(long idCategory, long idSubcategory) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (idCategory == item.getCategory().getId()) {
                if (idSubcategory == item.getCategory().getSubcategory().getId()) {
                    itemsDto.add(itemMapper.toItemDto(item));
                }
            }
        }
        return itemsDto;
    }

    public List<ItemDto> sortToMaterial(long id) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (id == item.getMaterial().getId()) {
                itemsDto.add(itemMapper.toItemDto(item));
                System.out.println(item);
             }
        }
        return itemsDto;
    }

    public List<ItemDto> sortToMaterialAndCategory(long idMaterial, long idCategory) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (idCategory == item.getCategory().getId()) {
                if (idMaterial == item.getMaterial().getId()) {
                    itemsDto.add(itemMapper.toItemDto(item));
                }
            }
        }
        return itemsDto;
    }

    public List<ItemDto> sortToMaterialAndCategoryAndSubcategory(long idMaterial, long idCategory, long idSubcategory) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (idCategory == item.getCategory().getId()) {
                if (idSubcategory == item.getCategory().getSubcategory().getId()) {
                    if (idMaterial == item.getMaterial().getId()) {
                        itemsDto.add(itemMapper.toItemDto(item));
                    }
                }
            }
        }
        return itemsDto;
    }

    public List<ItemDto> sortToPrice() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(itemMapper.toItemDto(item));
       }
        itemsDto.sort((i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice()));
        return itemsDto;
    }


    public List<ItemDto> sortToPriceAndCategoryAndSubcategory(long idCategory, long idSubcategory) {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            if (idCategory == item.getCategory().getId()) {
                if (idSubcategory == item.getCategory().getSubcategory().getId()) {
                    itemsDto.add(itemMapper.toItemDto(item));
                }
            }
        }
        itemsDto.sort((i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice()));
        return itemsDto;
    }

    public ItemDto findByLastCreatedAt(){
        Item item = itemRepository.findItemWithLatestCreatedAt();
        return itemMapper.toItemDto(item);
    }

    public ItemDto saveItem(Item item){
        itemRepository.save(item);
        return itemMapper.toItemDto(item);
    }
}
