package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.UserEntity;
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
public class UserEntityRepositoryIntegrationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserEntityRepositoryIntegrationTests( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testUserCanBeCreatedAndRecalled() {
        UserEntity user = TestDataUtil.createUserA();
        userRepository.save(user);
        Optional<UserEntity> result = userRepository.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testMultipleUserCanBeCreatedAndRecalled() {
        UserEntity userA = TestDataUtil.createUserA();
        UserEntity userB = TestDataUtil.createUserB();
        UserEntity userC = TestDataUtil.createUserC();
        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);
        Iterable<UserEntity> result = userRepository.findAll();
        assertThat(result).hasSize(3).containsExactly(userA, userB, userC);
    }

    @Test
    public void testUserCanBeUpdatedAndRecalled() {
        UserEntity userA = TestDataUtil.createUserA();
        userRepository.save(userA);
        userA.setFirstName("UPDATED");
        userRepository.save(userA);
        Optional<UserEntity> result = userRepository.findById(userA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userA);
    }

    @Test
    public void testUserCanBeDeleted() {
        UserEntity userA = TestDataUtil.createUserA();
        userRepository.save(userA);
        userRepository.delete(userA);
        Optional<UserEntity> result = userRepository.findById(userA.getId());
        assertThat(result).isEmpty();
    }
}
