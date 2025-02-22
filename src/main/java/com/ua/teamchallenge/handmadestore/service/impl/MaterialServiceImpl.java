package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.MaterialDto;
import com.ua.teamchallenge.handmadestore.mapper.MaterialMapper;
import com.ua.teamchallenge.handmadestore.model.Material;
import com.ua.teamchallenge.handmadestore.repository.MaterialRepository;

import java.util.List;

public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public List<MaterialDto> findAll() {
        List<Material> materials = materialRepository.findAll();

    }
}
