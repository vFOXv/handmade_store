package com.ua.teamchallenge.handmadestore.model;

import com.ua.teamchallenge.handmadestore.dto.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private List<CategoryFilterDto> categories;
    //private List<SubcategoryDto> subcategories;
    private List<MaterialDto> materials;
    private List<ColorDto> colors;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
