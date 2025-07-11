package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.BookingDto;
import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements Mapper<BookingEntity, BookingDto> {

    private ModelMapper modelMapper;
    @Autowired
    public BookingMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookingDto mapTo(BookingEntity bookingEntity) {
        return modelMapper.map(bookingEntity, BookingDto.class);
    }

    @Override
    public BookingEntity mapFrom(BookingDto bookingDto) {
        return modelMapper.map(bookingDto, BookingEntity.class);
    }
}
