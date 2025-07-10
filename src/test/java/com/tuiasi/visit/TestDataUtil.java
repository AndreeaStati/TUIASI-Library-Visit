package com.tuiasi.visit;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.AdminEntity;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;

import java.time.LocalDate;
import java.time.LocalTime;

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

}
