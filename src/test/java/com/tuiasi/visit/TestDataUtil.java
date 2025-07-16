package com.tuiasi.visit;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;


import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import com.tuiasi.visit.domain.entities.UserEntity;


public class TestDataUtil {

    public static AdminEntity createAdminA() {
        return AdminEntity.builder()
                .username("admin")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }

    public static AdminEntity createAdminB() {
        return AdminEntity.builder()
                .username("andreea")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }
    public static AdminEntity createAdminC() {
        return AdminEntity.builder()
                .username("alin")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }

    public static BlockedSlotsEntity createBlockedSlotA() {
        return BlockedSlotsEntity.builder()
                .date(LocalDate.parse("2025-07-09"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("13:00"))
                .reason("Intalnire rezervata")
                .build();
    }

    public static BlockedSlotsEntity createBlockedSlotB() {
        return BlockedSlotsEntity.builder()
                .date(LocalDate.parse("2025-07-10"))
                .startTime(LocalTime.parse("13:00"))
                .endTime(LocalTime.parse("14:00"))
                .reason("Mentenanta sistem")
                .build();
    }

    public static BlockedSlotsEntity createBlockedSlotC() {
        return BlockedSlotsEntity.builder()
                .date(LocalDate.parse("2025-07-11"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("12:00"))
                .reason("Blocare administrativa")
                .build();
    }

    public static BlockedSlotsEntity createTestBlockedSlotEntityA() {
        return BlockedSlotsEntity.builder()
                .id(1)
                .date(LocalDate.parse("2025-07-13"))
                .startTime(LocalTime.parse("13:00"))
                .endTime(LocalTime.parse("14:00"))
                .reason("Probleme tehnice")
                .build();
    }

    public static BlockedSlotsDto createTestBlockedSlotDtoA() {
        return BlockedSlotsDto.builder()
                .id(1)
                .date(LocalDate.parse("2025-07-13"))
                .startTime(LocalTime.parse("13:00"))
                .endTime(LocalTime.parse("14:00"))
                .reason("Probleme tehnice")
                .build();
    }


    public static UserEntity createUserA() {
        return UserEntity.builder()
                .firstName("alin")
                .lastName("admin")
                .email("ceva@student.tuiasi.ro")
                .phoneNumber("123456789")
                .build();
    }
    public static UserEntity createUserB() {
        return UserEntity.builder()
                .firstName("Andreea")
                .lastName("admin")
                .email("ceva@student.tuiasi.ro")
                .phoneNumber("123456789")
                .build();
    }
    public static UserEntity createUserC() {
        return UserEntity.builder()
                .firstName("Ciobanu")
                .lastName("admin")
                .email("ceva@student.tuiasi.ro")
                .phoneNumber("123456789")
                .build();
    }

    public static CategoryEntity createCategoryA() {
        return CategoryEntity.builder()
                .categoryName("Students")
                .pricePerPerson(0.0)
                .build();
    }

    public static CategoryEntity createCategoryB() {
        return CategoryEntity.builder()
                .categoryName("Adults")
                .pricePerPerson(10.0)
                .build();
    }

    public static CategoryEntity createCategoryC() {
        return CategoryEntity.builder()
                .categoryName("Tourist Group")
                .pricePerPerson(15.0)
                .build();
    }

    public static BookingEntity createBookingA(final UserEntity userEntity) {

        return BookingEntity.builder()
                .date(LocalDate.parse("2025-07-09"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("13:00"))
                .user(userEntity)
                .totalPrice( 30)
                .status("finalized")
                .build();
    }


    public static BookingEntity createBookingB(final UserEntity userEntity) {

        return BookingEntity.builder()
                .date(LocalDate.parse("2025-07-09"))
                .startTime(LocalTime.parse("11:00"))
                .endTime(LocalTime.parse("13:00"))
                .user(userEntity)
                .totalPrice( 30)
                .status("cancelled")
                .build();
    }

    public static BookingEntity createBookingC(final UserEntity userEntity) {

        return BookingEntity.builder()
                .date(LocalDate.parse("2026-07-09"))
                .startTime(LocalTime.parse("14:00"))
                .endTime(LocalTime.parse("15:00"))
                .user(userEntity)
                .totalPrice( 30)
                .status("unpaid")
                .build();
    }

    public static BookingDetailsEntity createBookingDetailsEntityA(BookingEntity bookingEntity, CategoryEntity categoryEntity) {

        BookingDetailsId id = new BookingDetailsId(bookingEntity.getId(), categoryEntity.getId());

        return BookingDetailsEntity.builder()
                .id(id)
                .booking(bookingEntity)
                .category(categoryEntity)
                .numberOfUsers(3)
                .build();
    }

    public static BookingDetailsEntity createBookingDetailsEntity(BookingEntity booking, CategoryEntity category, int persons) {
        BookingDetailsId id = new BookingDetailsId(booking.getId(), category.getId());
        return BookingDetailsEntity.builder()
                .id(id)
                .booking(booking)
                .category(category)
                .numberOfUsers(persons)
                .build();

    public static PaymentEntity createPaymentA(final BookingEntity bookingEntity) {

        return PaymentEntity.builder()
                .amount(20)
                .paymentDate(LocalDate.parse("2025-07-09"))
                .booking(bookingEntity)
                .build();

    }

    public static PaymentEntity createPaymentB(final BookingEntity bookingEntity) {

        return PaymentEntity.builder()
                .amount(2023)
                .paymentDate(LocalDate.parse("2025-07-09"))
                .booking(bookingEntity)
                .build();

    }

    public static PaymentEntity createPaymentC(final BookingEntity bookingEntity) {

        return PaymentEntity.builder()
                .amount(255)
                .paymentDate(LocalDate.parse("2055-07-09"))
                .booking(bookingEntity)
                .build();

    }
}
