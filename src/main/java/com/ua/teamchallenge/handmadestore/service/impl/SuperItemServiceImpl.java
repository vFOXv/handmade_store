package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.SuperItemDto;
import com.ua.teamchallenge.handmadestore.mapper.SuperItemMapper;
import com.ua.teamchallenge.handmadestore.model.SuperItem;
import com.ua.teamchallenge.handmadestore.repository.SuperItemRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperItemServiceImpl {
    private final SuperItemRepository superItemRepository;
    private final SuperItemMapper superItemMapper;


    public Page<SuperItemDto> findAll(Pageable pageable) {

        return superItemRepository.findAll(pageable)
                .map(superItemMapper::toSuperItemDto);
    }

    public SuperItem save(SuperItem superItem) {

        return superItemRepository.save(superItem);
    }
}
