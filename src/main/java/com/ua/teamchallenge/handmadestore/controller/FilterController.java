package com.ua.teamchallenge.handmadestore.controller;

import com.ua.teamchallenge.handmadestore.model.Filter;
import com.ua.teamchallenge.handmadestore.service.impl.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowCredentials = "true")
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/all")
    public Filter getFilters() {
        return filterService.getFilters();
    }
}
