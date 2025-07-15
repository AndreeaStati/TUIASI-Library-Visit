package com.tuiasi.visit.mappers.impl;

import com.tuiasi.visit.domain.dto.PaymentDto;
import com.tuiasi.visit.domain.entities.PaymentEntity;
import com.tuiasi.visit.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperImpl implements Mapper<PaymentEntity, PaymentDto> {

    private ModelMapper modelMapper;
    @Autowired
    public PaymentMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentEntity mapFrom(PaymentDto paymentDto) {
        return modelMapper.map(paymentDto, PaymentEntity.class);
    }

    @Override
    public PaymentDto mapTo(PaymentEntity paymentEntity) {
        return modelMapper.map(paymentEntity, PaymentDto.class);
    }

}
