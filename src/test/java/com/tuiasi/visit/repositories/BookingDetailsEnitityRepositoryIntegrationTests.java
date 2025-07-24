package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.*;
import org.junit.jupiter.api.BeforeEach;
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
public class BookingDetailsEnitityRepositoryIntegrationTests {

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public BookingDetailsEnitityRepositoryIntegrationTests(BookingDetailsRepository bookingDetailsRepository, BookingRepository bookingRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingDetailsRepository = bookingDetailsRepository;
    }

    @BeforeEach
    void clearDatabase() {
        bookingDetailsRepository.deleteAll();
    }


    @Test
    public void testThatBookingDetailsCanBeCreatedAndRecalled(){

        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);

        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);

        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);

        BookingDetailsEntity bookingDetailsEntity = TestDataUtil.createBookingDetailsEntityA(bookingEntity, categoryEntity);
        bookingDetailsRepository.save(bookingDetailsEntity);

        BookingDetailsId id = new BookingDetailsId(bookingEntity.getId(), categoryEntity.getId());
        Optional<BookingDetailsEntity> result = bookingDetailsRepository.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookingDetailsEntity);
    }

    @Test
    public void testThatMultipleBookingDetailsCanBeCreatedAndRecalled(){

        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);

        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);

        CategoryEntity categoryEntityA = TestDataUtil.createCategoryA();
        CategoryEntity categoryEntityB = TestDataUtil.createCategoryB();
        CategoryEntity categoryEntityC = TestDataUtil.createCategoryC();

        categoryRepository.save(categoryEntityA);
        categoryRepository.save(categoryEntityB);
        categoryRepository.save(categoryEntityC);

        BookingDetailsEntity bookingDetailsEntityA = TestDataUtil.createBookingDetailsEntity(bookingEntity, categoryEntityA, 2);
        BookingDetailsEntity bookingDetailsEntityB = TestDataUtil.createBookingDetailsEntity(bookingEntity, categoryEntityB, 4);
        BookingDetailsEntity bookingDetailsEntityC = TestDataUtil.createBookingDetailsEntity(bookingEntity, categoryEntityC, 6);

        bookingDetailsRepository.save(bookingDetailsEntityA);
        bookingDetailsRepository.save(bookingDetailsEntityB);
        bookingDetailsRepository.save(bookingDetailsEntityC);

        Iterable<BookingDetailsEntity> result = bookingDetailsRepository.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(bookingDetailsEntityA, bookingDetailsEntityB, bookingDetailsEntityC);
    }


    @Test
    public void testThatBookingDetailsCanBeUpdated() {
        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);

        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);

        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);

        BookingDetailsEntity bookingDetails = TestDataUtil.createBookingDetailsEntity(bookingEntity, categoryEntity, 2);
        bookingDetailsRepository.save(bookingDetails);

        bookingDetails.setNumberOfUsers(5);
        bookingDetailsRepository.save(bookingDetails);

        BookingDetailsId id = new BookingDetailsId(bookingEntity.getId(), categoryEntity.getId());
        Optional<BookingDetailsEntity> result = bookingDetailsRepository.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get().getNumberOfUsers()).isEqualTo(5);
        assertThat(result.get()).isEqualTo(bookingDetails);
    }

    @Test
    public void testThatBookingDetailsCanBeDeleted() {

        UserEntity userEntity = TestDataUtil.createUserA();
        userRepository.save(userEntity);

        BookingEntity bookingEntity = TestDataUtil.createBookingA(userEntity);
        bookingRepository.save(bookingEntity);

        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);

        BookingDetailsEntity bookingDetails = TestDataUtil.createBookingDetailsEntity(bookingEntity, categoryEntity, 2);
        bookingDetailsRepository.save(bookingDetails);

        BookingDetailsId id = new BookingDetailsId(bookingEntity.getId(), categoryEntity.getId());

        bookingDetailsRepository.deleteById(id);
        Optional<BookingDetailsEntity> result = bookingDetailsRepository.findById(id);
        assertThat(result).isEmpty();
    }

}
