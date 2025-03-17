package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.ITEM_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public Page<ItemDto> findAll(Pageable pageable) {
        return itemRepository.findDistinctItems(pageable)
                .map(itemMapper::toItemDto);
    }

    @Transactional(readOnly = true)
    public ItemDto findById(long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ITEM_NOT_FOUND_BY_ID, id)));
        return itemMapper.toItemDto(item);
    }

    public Page<ItemDto> findByCategoryId(long id, Pageable pageable) {
        return itemRepository.findDistinctByCategoryId(id, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findBySubcategoryId(long id, Pageable pageable) {
        return itemRepository.findDistinctByCategorySubcategoryId(id, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByCategoryIdAndSubcategoryId(long categoryId, long subcategoryId, Pageable pageable) {
        return itemRepository.findDistinctByCategoryIdAndCategorySubcategoryId(categoryId, subcategoryId, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByMaterialId(long id, Pageable pageable) {
        return itemRepository.findDistinctByMaterialId(id, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByMaterialIdAndCategoryId(long materialId, long categoryId, Pageable pageable) {
        return itemRepository.findDistinctByMaterialIdAndCategoryId(materialId, categoryId, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByMaterialIdAndCategoryIdAndSubcategoryId(long materialId, long categoryId, long subcategoryId, Pageable pageable) {
        return itemRepository.findDistinctByMaterialIdAndCategoryIdAndCategorySubcategoryId(materialId, categoryId, subcategoryId, pageable)
                .map(itemMapper::toItemDto);
    }

    public ItemDto findWithLastDate(){
        Item item = itemRepository.findItemWithLatestCreatedAt()
                .orElseThrow();
        return itemMapper.toItemDto(item);
    }

    public ItemDto saveItem(Item item){
        itemRepository.save(item);
        return itemMapper.toItemDto(item);
    }
}
