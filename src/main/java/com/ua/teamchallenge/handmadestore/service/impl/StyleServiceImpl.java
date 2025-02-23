package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.StyleDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.StyleMapper;
import com.ua.teamchallenge.handmadestore.model.Style;
import com.ua.teamchallenge.handmadestore.repository.StyleRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.COLOR_NOT_FOUND_BY_ID;



@Service
public class StyleServiceImpl implements com.ua.teamchallenge.handmadestore.service.StyleService {
    private final StyleRepository styleRepository;
    private final StyleMapper styleMapper;

    public StyleServiceImpl(StyleRepository styleRepository, StyleMapper styleMapper) {
        this.styleRepository = styleRepository;
        this.styleMapper = styleMapper;
    }

    public List<StyleDto> findAll() {
        List<Style> styles = styleRepository.findAll();
        List<StyleDto> stylesDto = new ArrayList<StyleDto>();
        for(Style style : styles) {
            stylesDto.add(styleMapper.toStyleDto(style));
        }
        return stylesDto;
    }

    public StyleDto findById(Long id) {
        Style style = styleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(COLOR_NOT_FOUND_BY_ID + id)));
        return styleMapper.toStyleDto(style);
    }
}
