package com.tuiasi.visit;

import com.tuiasi.visit.domain.entities.AdminEntity;

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
}
