package com.tuiasi.visit;

import com.tuiasi.visit.domain.entities.AdminEntity;
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
}
