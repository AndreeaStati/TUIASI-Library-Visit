package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.CategoryDto;
import com.tuiasi.visit.domain.entities.CategoryEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements Mapper<CategoryEntity, CategoryDto> {

    private final ModelMapper modelMapper;

    public CategoryMapperImpl() {
        modelMapper = new ModelMapper();
    }


    @Override
    public CategoryDto mapTo(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    @Override
    public CategoryEntity mapFrom(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, CategoryEntity.class);
    }
}
