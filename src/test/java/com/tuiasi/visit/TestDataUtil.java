package com.tuiasi.visit;

import com.tuiasi.visit.domain.Admin;

public class TestDataUtil {

    public static Admin createAdminA() {
        return Admin.builder()
                .username("admin")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }

    public static Admin createAdminB() {
        return Admin.builder()
                .username("andreea")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }
    public static Admin createAdminC() {
        return Admin.builder()
                .username("alin")
                .hash_password("admin")
                .email("admin@gmail.com")
                .build();
    }
}
