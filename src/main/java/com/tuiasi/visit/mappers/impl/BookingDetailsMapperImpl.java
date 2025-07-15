package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.BookingDetailsDto;
import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingDetailsMapperImpl implements Mapper<BookingDetailsEntity, BookingDetailsDto> {
    private ModelMapper modelMapper;

    public BookingDetailsMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookingDetailsDto mapTo(BookingDetailsEntity bookingDetailsEntity) {
        return modelMapper.map(bookingDetailsEntity, BookingDetailsDto.class);
    }

    @Override
    public BookingDetailsEntity mapFrom(BookingDetailsDto bookingDetailsDto) {
        return modelMapper.map(bookingDetailsDto, BookingDetailsEntity.class);
    }
}
