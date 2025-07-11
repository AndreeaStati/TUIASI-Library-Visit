package com.tuiasi.visit;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.AdminEntity;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public static CategoriesEntity createCategoryA() {
        return CategoriesEntity.builder()
                .categoryName("Students")
                .pricePerPerson(0.0)
                .build();
    }

    public static CategoriesEntity createCategoryB() {
        return CategoriesEntity.builder()
                .categoryName("Adults")
                .pricePerPerson(10.0)
                .build();
    }

    public static CategoriesEntity createCategoryC() {
        return CategoriesEntity.builder()
                .categoryName("Tourist Group")
                .pricePerPerson(15.0)
                .build();
    }
}
