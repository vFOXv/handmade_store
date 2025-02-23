package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapperConfig;
import com.ua.teamchallenge.handmadestore.dto.CategoryDto;
import com.ua.teamchallenge.handmadestore.dto.MaterialDto;
import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.Material;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface MaterialMapper {

    MaterialDto toMaterialDto(Material material);
    Material toMaterial(MaterialDto materialDto);

}
