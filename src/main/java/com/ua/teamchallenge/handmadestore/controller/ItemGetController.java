package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.dto.ItemDto;
import com.ua.teamchallenge.handmadestore.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/get")
@RequiredArgsConstructor
public class ItemGetController {

    private final ItemServiceImpl itemService;

    @GetMapping("/items")
    public List<ItemDto> findAllItems(){
        return itemService.findAll();
    }

    @GetMapping("/item/{id}")
    public ItemDto findById(@PathVariable("id") long id){
        return itemService.findById(id);
    }

    @GetMapping("/category/{id}")
    public List<ItemDto> findByCategory(@PathVariable("id") long id){
        return itemService.sortToCategory(id);
    }

    @GetMapping("/subcategory/{id}")
    public List<ItemDto> findBySubcategory(@PathVariable long id){
        return itemService.sortToSubcategory(id);
    }

    @GetMapping("/category/subcategory?category={idCategory}&subcategory={idSubcategory}")
    public List<ItemDto> findBySubcategoryAndCategory(@RequestParam (value = "category", required = false) long idCategory,
                                                      @RequestParam (value = "subcategory", required = false) long idSubcategory){
        return itemService.sortToCategoryAndSubcategory(idCategory, idSubcategory);
    }

    @GetMapping("/material/{id}")
    public List<ItemDto> findByMaterial(@PathVariable("id") long id){
        return itemService.sortToMaterial(id);
    }

    @GetMapping("/category/material?category={idCategory}&material={idMaterial}")
    public List<ItemDto> findByCategoryAndMaterial(@RequestParam (value = "category", required = false)long idCategory,
                                                   @RequestParam (value = "material", required = false)long idMaterial){
        return itemService.sortToMaterialAndCategory(idMaterial, idCategory);
    }

    @GetMapping("/category/subcategory/material?category={idCategory}&subcategory={idSubcategory}&material={idMaterial}")
    public List<ItemDto> findByMaterialAndCategoryAndSubcategory(@RequestParam (value = "category", required = false) long idCategory,
                                                                 @RequestParam (value = "subcategory", required = false)long idSubcategory,
                                                                 @RequestParam (value = "material", required = false)long idMaterial){
        return itemService.sortToMaterialAndCategoryAndSubcategory(idMaterial, idCategory, idSubcategory);
    }

    @GetMapping("/price")
    public List<ItemDto> sortByPrice(){
        return itemService.sortToPrice();
    }

    @GetMapping("/category/subcategory/price?category={idCategory}&subcategory={idSubcategory}")
    public List<ItemDto> sortToPriceAndCategoryAndSubcategory(@RequestParam (value = "category", required = false) long idCategory,
                                                              @RequestParam (value = "subcategory", required = false) long idSubcategory){
        return itemService.sortToPriceAndCategoryAndSubcategory(idCategory, idSubcategory);
    }

    @GetMapping("/last_date")
    public ItemDto findLastDate(){
        return itemService.findByLastCreatedAt();
    }
}
