package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.PaymentEntity;
import com.tuiasi.visit.repositories.PaymentRepository;
import com.tuiasi.visit.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentEntity createPayment(PaymentEntity paymentEntity) {
        return paymentRepository.save(paymentEntity);
    }

    @Override
    public boolean existsById(Integer id) {
        return paymentRepository.existsById(id);
    }

    @Override
    public PaymentEntity updatePayment(Integer id, PaymentEntity paymentEntity) {
        paymentEntity.setId(id);
        return paymentRepository.findById(id).map(existingPayment ->{
            Optional.ofNullable(paymentEntity.getBooking()).ifPresent(existingPayment::setBooking);
            Optional.ofNullable(paymentEntity.getPaymentDate()).ifPresent(existingPayment::setPaymentDate);
            Optional.ofNullable(paymentEntity.getAmount()).ifPresent(existingPayment::setAmount);
            return paymentRepository.save(existingPayment);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<PaymentEntity> findAllPayments() {
        return StreamSupport.stream(paymentRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentEntity> findPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        paymentRepository.deleteById(id);
    }
}
