package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.domain.entities.PaymentEntity;
import com.tuiasi.visit.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PaymentEntityRepositoryIntegrationTests {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentEntityRepositoryIntegrationTests(PaymentRepository paymentRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Test
    public void testThatPaymentEntityCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);
        PaymentEntity paymentEntity = TestDataUtil.createPaymentA(bookingEntity);
        paymentRepository.save(paymentEntity);

        Optional<PaymentEntity> result = paymentRepository.findById(paymentEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(paymentEntity);
    }

    @Test
    public void testThatMultiplePaymentEntitiesCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        BookingEntity bookingEntityB = TestDataUtil.createBookingB(userEntity);
        bookingRepository.save(bookingEntityB);
        BookingEntity bookingEntityC = TestDataUtil.createBookingC(userEntity);
        bookingRepository.save(bookingEntityC);

        PaymentEntity paymentEntityA = TestDataUtil.createPaymentA(bookingEntityA);
        paymentRepository.save(paymentEntityA);
        PaymentEntity paymentEntityB = TestDataUtil.createPaymentB(bookingEntityB);
        paymentRepository.save(paymentEntityB);
        PaymentEntity paymentEntityC = TestDataUtil.createPaymentC(bookingEntityC);
        paymentRepository.save(paymentEntityC);

        Iterable<PaymentEntity> result = paymentRepository.findAll();
        assertThat(result).isNotNull().containsExactly(paymentEntityA, paymentEntityB, paymentEntityC);
    }

    @Test
    public void testThatPaymentEntityCanBeUpdatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        PaymentEntity paymentEntityA = TestDataUtil.createPaymentA(bookingEntityA);
        paymentRepository.save(paymentEntityA);
        paymentEntityA.setAmount(2);
        paymentRepository.save(paymentEntityA);
        Optional<PaymentEntity> result = paymentRepository.findById(paymentEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(paymentEntityA);
    }

    @Test
    public void testThatPaymentEntityCanBeDeletedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        PaymentEntity paymentEntityA = TestDataUtil.createPaymentA(bookingEntityA);
        paymentRepository.save(paymentEntityA);
        paymentRepository.deleteById(paymentEntityA.getId());
        Optional<PaymentEntity> result = paymentRepository.findById(paymentEntityA.getId());
        assertThat(result).isEmpty();
    }
}
