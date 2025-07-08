package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.AdminDto;
import com.tuiasi.visit.domain.entities.AdminEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements Mapper<AdminEntity, AdminDto> {

    private final ModelMapper modelMapper;

    public AdminMapperImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public AdminDto mapTo(AdminEntity adminEntity) {
        return modelMapper.map(adminEntity, AdminDto.class);
    }

    @Override
    public AdminEntity mapFrom(AdminDto adminDto) {
        return modelMapper.map(adminDto, AdminEntity.class);
    }
}
