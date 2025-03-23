package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ItemMapper;
import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return itemRepository.findAll(pageable)
                .map(itemMapper::toItemDto);
        // Ленивая загрузка связанных сущностей
//        itemPage.getContent().forEach(item -> {
//            item.getCategory().getSubcategory();
//        });
//        return itemPage.map(itemMapper::toItemDto);
    }

    @Transactional(readOnly = true)
    public ItemDto findById(long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ITEM_NOT_FOUND_BY_ID, id)));
        return itemMapper.toItemDto(item);
    }

    public Page<ItemDto> findByCategoryId(long id, Pageable pageable) {
        return itemRepository.findByCategoryId(id, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findBySubcategoryId(long id, Pageable pageable) {
        return itemRepository.findByCategorySubcategoryId(id, pageable)
                .map(itemMapper::toItemDto);
    }

//    public Page<ItemDto> findByCategoryIdAndSubcategoryId(long categoryId, long subcategoryId, Pageable pageable) {
//        return itemRepository.findByCategoryIdAndCategorySubcategoryId(categoryId, subcategoryId, pageable)
//                .map(itemMapper::toItemDto);
//    }

    public Page<ItemDto> findByMaterialId(long id, Pageable pageable) {
        return itemRepository.findByMaterialId(id, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByMaterialIdAndCategoryId(long materialId, long categoryId, Pageable pageable) {
        return itemRepository.findByMaterialIdAndCategoryId(materialId, categoryId, pageable)
                .map(itemMapper::toItemDto);
    }

    public Page<ItemDto> findByMaterialIdAndCategoryIdAndSubcategoryId(long materialId, long categoryId, long subcategoryId, Pageable pageable) {
        return itemRepository.findByMaterialIdAndCategoryIdAndCategorySubcategoryId(materialId, categoryId, subcategoryId, pageable)
                .map(itemMapper::toItemDto);
    }

    public ItemDto findWithLastDate() {
        Item item = itemRepository.findItemWithLatestCreatedAt()
                .orElseThrow();
        return itemMapper.toItemDto(item);
    }

    public Page<ItemDto> sortToPriceAllGrow(Pageable pageable) {
        Pageable sortedByPrice = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("price").ascending() // или .descending() для убывания
        );

        // Получаем страницу из репозитория и преобразуем в DTO
        Page<Item> itemsPage = itemRepository.findAll(sortedByPrice);
        return itemsPage.map(itemMapper::toItemDto);
    }

    public Page<ItemDto> sortToPriceAllDrop(Pageable pageable) {
        // Если нужно явно указать сортировку по price, можно создать Pageable с нужной сортировкой
        Pageable sortedByPrice = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("price").descending() // или .descending() для убывания
        );

        // Получаем страницу из репозитория и преобразуем в DTO
        Page<Item> itemsPage = itemRepository.findAll(sortedByPrice);
        return itemsPage.map(itemMapper::toItemDto);
    }

    public ItemDto saveItem(Item item) {
        itemRepository.save(item);
        return itemMapper.toItemDto(item);
    }
}
