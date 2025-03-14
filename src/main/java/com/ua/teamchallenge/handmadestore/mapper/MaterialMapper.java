package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.MaterialDto;
import com.ua.teamchallenge.handmadestore.model.Material;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface MaterialMapper {
    MaterialDto toMaterialDto(Material material);

    Material toMaterial(MaterialDto materialDto);
}
