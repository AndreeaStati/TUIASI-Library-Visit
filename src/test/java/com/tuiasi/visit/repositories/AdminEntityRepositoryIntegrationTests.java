package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.AdminEntity;
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
public class AdminEntityRepositoryIntegrationTests {

    private AdminRepository adminRepository;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Test
    public void testAdminCanBeCreatedAndRecalled() {
        AdminEntity adminEntity = TestDataUtil.createAdminA();
        adminRepository.save(adminEntity);
        Optional<AdminEntity> result = adminRepository.findById(adminEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(adminEntity);
    }

    @Test
    public void thestMultipleAdminsCanBeCreatedAndRecalled() {
        AdminEntity adminEntityA = TestDataUtil.createAdminA();
        AdminEntity adminEntityB = TestDataUtil.createAdminB();
        AdminEntity adminEntityC = TestDataUtil.createAdminC();
        adminRepository.save(adminEntityA);
        adminRepository.save(adminEntityB);
        adminRepository.save(adminEntityC);

        Iterable<AdminEntity> result = adminRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .contains(adminEntityA, adminEntityB, adminEntityC);
    }

    @Test
    public void testAdminCanBeUpdatedAndRecalled() {
        AdminEntity adminEntity = TestDataUtil.createAdminA();
        adminRepository.save(adminEntity);
        adminEntity.setUsername("UPDATED");
        adminRepository.save(adminEntity);
        Optional<AdminEntity> result = adminRepository.findById(adminEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(adminEntity);
    }

    @Test
    public void testAdminCanBeDeleted() {
        AdminEntity adminEntity = TestDataUtil.createAdminA();
        adminRepository.save(adminEntity);
        adminRepository.delete(adminEntity);
        Optional<AdminEntity> result = adminRepository.findById(adminEntity.getId());
        assertThat(result).isEmpty();
    }
}
