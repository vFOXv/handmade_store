package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.model.Filter;
import com.ua.teamchallenge.handmadestore.service.impl.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/all")
    public Filter getFilters() {
        return filterService.getFilters();
    }
}
