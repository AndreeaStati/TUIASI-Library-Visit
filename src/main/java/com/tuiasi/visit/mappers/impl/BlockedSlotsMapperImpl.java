package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BlockedSlotsMapperImpl implements Mapper<BlockedSlotsEntity, BlockedSlotsDto> {

    private final ModelMapper modelMapper;

    public BlockedSlotsMapperImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public BlockedSlotsDto mapTo(BlockedSlotsEntity blockedSlotsEntity) {
        return modelMapper.map(blockedSlotsEntity, BlockedSlotsDto.class);
    }

    @Override
    public BlockedSlotsEntity mapFrom(BlockedSlotsDto blockedSlotsDto) {
        return modelMapper.map(blockedSlotsDto, BlockedSlotsEntity.class);
    }
}
