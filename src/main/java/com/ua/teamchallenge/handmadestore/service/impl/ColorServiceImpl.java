package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.ColorDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.ColorMapper;
import com.ua.teamchallenge.handmadestore.model.Color;
import com.ua.teamchallenge.handmadestore.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.STYLE_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Transactional(readOnly = true)
    public List<ColorDto> findAll(){
        List<Color> colors = colorRepository.findAll();
        List<ColorDto> colorsDtos = new ArrayList<>();
        for (Color color : colors) {
            colorsDtos.add(colorMapper.toColorDto(color));
        }
        return colorsDtos;
    }

    @Transactional(readOnly = true)
    public ColorDto findById(Long id){
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(STYLE_NOT_FOUND_BY_ID, id)));
        return colorMapper.toColorDto(color);
    }
}
