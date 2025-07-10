package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.UserDto;
import com.tuiasi.visit.domain.entities.UserEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
