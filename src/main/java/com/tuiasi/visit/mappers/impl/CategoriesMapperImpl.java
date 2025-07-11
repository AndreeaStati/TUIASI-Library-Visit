package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.dto.CategoriesDto;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapperImpl implements Mapper<CategoriesEntity, CategoriesDto> {

    private final ModelMapper modelMapper;

    public CategoriesMapperImpl() {
        modelMapper = new ModelMapper();
    }


    @Override
    public CategoriesDto mapTo(CategoriesEntity categoriesEntity) {
        return modelMapper.map(categoriesEntity, CategoriesDto.class);
    }

    @Override
    public CategoriesEntity mapFrom(CategoriesDto categoriesDto) {
        return modelMapper.map(categoriesDto, CategoriesEntity.class);
    }
}
