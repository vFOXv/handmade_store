package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemGetController {
    private final ItemServiceImpl itemService;

    @GetMapping("")
    public PagedModel<ItemDto> findAllItems(@PageableDefault Pageable pageable) {
        return new PagedModel<>(itemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable long id){
        return ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping(params = "categoryId")
    public PagedModel<ItemDto> findByCategory(@RequestParam long categoryId,
                                              @PageableDefault Pageable pageable){
        return new PagedModel<>(itemService.findByCategoryId(categoryId, pageable));
    }

    @GetMapping(params = "subcategoryId")
    public PagedModel<ItemDto> findBySubcategory(@RequestParam(required = false) long subcategoryId,
                                                 @PageableDefault Pageable pageable){
        return new PagedModel<>(itemService.findBySubcategoryId(subcategoryId, pageable));
    }

    @GetMapping(params = {"categoryId", "subcategoryId"})
    public PagedModel<ItemDto> findBySubcategoryAndCategory(@RequestParam(required = false) long categoryId,
                                                            @RequestParam(required = false) long subcategoryId,
                                                            @PageableDefault Pageable pageable){
        return new PagedModel<>(itemService.findByCategoryIdAndSubcategoryId(categoryId, subcategoryId, pageable));
    }

    @GetMapping(params = "materialId")
    public PagedModel<ItemDto> findByMaterial(@RequestParam(required = false) long materialId,
                                              @PageableDefault Pageable pageable) {
        return new PagedModel<>(itemService.findByMaterialId(materialId, pageable));
    }

    @GetMapping(params = {"materialId", "categoryId"})
    public PagedModel<ItemDto> findByMaterialAndCategory(@RequestParam(required = false) long materialId,
                                                         @RequestParam(required = false) long categoryId,
                                                         @PageableDefault Pageable pageable) {
        return new PagedModel<>(itemService.findByMaterialIdAndCategoryId(materialId, categoryId, pageable));
    }

    @GetMapping(params = {"materialId", "categoryId", "subcategoryId"})
    public PagedModel<ItemDto> findByMaterialAndCategoryAndSubcategory(@RequestParam(required = false) long materialId,
                                                                       @RequestParam(required = false) long categoryId,
                                                                       @RequestParam(required = false) long subcategoryId,
                                                                       @PageableDefault Pageable pageable){
        return new PagedModel<>(itemService.findByMaterialIdAndCategoryIdAndSubcategoryId(materialId, categoryId, subcategoryId, pageable));
    }

    @GetMapping(params = "lastDate")
    public ItemDto findWithLastDate(){
        return itemService.findWithLastDate();
    }
}
