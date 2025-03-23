package com.ua.teamchallenge.handmadestore.mapper;

import com.ua.teamchallenge.handmadestore.config.MapStructConfig;
import com.ua.teamchallenge.handmadestore.dto.ImageDto;
import com.ua.teamchallenge.handmadestore.model.Image;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface ImageMapper {
    ImageDto toImageDto(Image image);

    Image toImage(ImageDto imageDto);
}
