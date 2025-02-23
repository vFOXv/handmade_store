package com.ua.teamchallenge.handmadestore.service.impl;

import com.ua.teamchallenge.handmadestore.dto.MaterialDto;
import com.ua.teamchallenge.handmadestore.exception.EntityNotFoundException;
import com.ua.teamchallenge.handmadestore.mapper.MaterialMapper;
import com.ua.teamchallenge.handmadestore.model.Material;
import com.ua.teamchallenge.handmadestore.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.ua.teamchallenge.handmadestore.util.ServiceConstants.MATERIAL_NOT_FOUND_BY_ID;

@Service
public class MaterialServiceImpl implements com.ua.teamchallenge.handmadestore.service.MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public List<MaterialDto> findAll() {
        List<Material> materials = materialRepository.findAll();
        List<MaterialDto> materialDtos = new ArrayList<>();
        for(Material material : materials) {
            materialDtos.add(materialMapper.toMaterialDto(material));
        }
        return materialDtos;
    }

    public MaterialDto findById(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MATERIAL_NOT_FOUND_BY_ID + id)));
        return materialMapper.toMaterialDto(material);
    }
}
