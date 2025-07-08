package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.Admin;
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
public class AdminRepositoryIntegrationTests {

    private AdminRepository adminRepository;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Test
    public void testAdminCanBeCreatedAndRecalled() {
        Admin admin = TestDataUtil.createAdminA();
        adminRepository.save(admin);
        Optional<Admin> result = adminRepository.findById(admin.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(admin);
    }

    @Test
    public void thestMultipleAdminsCanBeCreatedAndRecalled() {
        Admin adminA = TestDataUtil.createAdminA();
        Admin adminB = TestDataUtil.createAdminB();
        Admin adminC = TestDataUtil.createAdminC();
        adminRepository.save(adminA);
        adminRepository.save(adminB);
        adminRepository.save(adminC);

        Iterable<Admin> result = adminRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .contains(adminA, adminB, adminC);
    }

    @Test
    public void testAdminCanBeUpdatedAndRecalled() {
        Admin admin = TestDataUtil.createAdminA();
        adminRepository.save(admin);
        admin.setUsername("alin");
        adminRepository.save(admin);
        Optional<Admin> result = adminRepository.findById(admin.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(admin);
    }

    @Test
    public void testAdminCanBeDeleted() {
        Admin admin = TestDataUtil.createAdminA();
        adminRepository.save(admin);
        adminRepository.delete(admin);
        Optional<Admin> result = adminRepository.findById(admin.getId());
        assertThat(result).isEmpty();
    }
}
