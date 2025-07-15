package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    PaymentEntity createPayment(PaymentEntity bookingEntity);

    boolean existsById(Integer id);

    PaymentEntity updatePayment(Integer id, PaymentEntity paymentEntity);

    List<PaymentEntity> findAllPayments();

    Optional<PaymentEntity> findPaymentById(Integer id);

    void deleteById(Integer id);
}
