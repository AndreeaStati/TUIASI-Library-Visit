package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.PaymentDto;
import com.tuiasi.visit.domain.entities.PaymentEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.repositories.PaymentRepository;
import com.tuiasi.visit.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final Mapper<PaymentEntity, PaymentDto> paymentMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, Mapper<PaymentEntity, PaymentDto> paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @PostMapping(path = "/payments")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentEntity paymentEntity = paymentMapper.mapFrom(paymentDto);
        PaymentEntity savedPaymentEntity = paymentService.createPayment(paymentEntity);
        return new ResponseEntity<>(paymentMapper.mapTo(savedPaymentEntity),HttpStatus.CREATED);
    }

    @GetMapping(path = "/payments")
    public List<PaymentDto> findAllPayments() {
        List<PaymentEntity> payments = paymentService.findAllPayments();
        return payments.stream()
                .map(paymentMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/payments/{id}")
    public ResponseEntity<PaymentDto> findPaymentById(@PathVariable Integer id) {
        Optional<PaymentEntity> paymentEntity = paymentService.findPaymentById(id);
        return paymentEntity.map( result -> {
            PaymentDto paymentDto = paymentMapper.mapTo(result);
            return new ResponseEntity<>(paymentDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/payments/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Integer id, @RequestBody PaymentDto paymentDto) {
        if(!paymentService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PaymentEntity paymentEntity = paymentMapper.mapFrom(paymentDto);
        PaymentEntity savedPaymentEntity = paymentService.updatePayment(id, paymentEntity);
        return new ResponseEntity<>(paymentMapper.mapTo(savedPaymentEntity),HttpStatus.OK);
    }

    @DeleteMapping(path = "/payments/{id}")
    public ResponseEntity deletePaymentById(@PathVariable Integer id) {
        paymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
