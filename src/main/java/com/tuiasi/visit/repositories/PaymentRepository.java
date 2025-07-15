package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
}
