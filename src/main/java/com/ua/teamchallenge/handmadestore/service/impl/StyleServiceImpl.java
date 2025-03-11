package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.StyleDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.StyleMapper;
import com.ua.teamchallenge.handmadestore.model.Style;
import com.ua.teamchallenge.handmadestore.repository.StyleRepository;
import com.ua.teamchallenge.handmadestore.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.COLOR_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;
    private final StyleMapper styleMapper;

    @Transactional(readOnly = true)
    public List<StyleDto> findAll() {
        List<Style> styles = styleRepository.findAll();
        List<StyleDto> stylesDto = new ArrayList<>();
        for(Style style : styles) {
            stylesDto.add(styleMapper.toStyleDto(style));
        }
        return stylesDto;
    }

    @Transactional(readOnly = true)
    public StyleDto findById(Long id) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(COLOR_NOT_FOUND_BY_ID, id)));
        return styleMapper.toStyleDto(style);
    }
}
