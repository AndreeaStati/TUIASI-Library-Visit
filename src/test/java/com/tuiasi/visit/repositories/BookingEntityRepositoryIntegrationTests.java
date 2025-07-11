package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookingEntityRepositoryIntegrationTests {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingEntityRepositoryIntegrationTests(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Test
    public void testThatBookingEntityCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);
        Optional<BookingEntity> result = bookingRepository.findById(bookingEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookingEntity);
    }

    @Test
    public void testThatMultipleBookingsCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        BookingEntity bookingEntityB = TestDataUtil.createBookingA(userEntity);
        BookingEntity bookingEntityC = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        bookingRepository.save(bookingEntityB);
        bookingRepository.save(bookingEntityC);
        Iterable<BookingEntity> result = bookingRepository.findAll();
        assertThat(result).hasSize(3).containsExactly(bookingEntityA, bookingEntityB, bookingEntityC);
    }

    @Test
    public void testThatBookingEntityCanBeUpdatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        bookingEntityA.setStatus("UPDATED");
        bookingRepository.save(bookingEntityA);
        Optional<BookingEntity> result = bookingRepository.findById(bookingEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookingEntityA);
    }

    @Test
    public void testThatBookingEntityCanBeDeletedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);
        BookingEntity bookingEntityA = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntityA);
        bookingRepository.deleteById(bookingEntityA.getId());
        Optional<BookingEntity> result = bookingRepository.findById(bookingEntityA.getId());
        assertThat(result).isEmpty();
    }
}
