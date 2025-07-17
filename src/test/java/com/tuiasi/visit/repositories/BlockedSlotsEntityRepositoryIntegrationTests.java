package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BlockedSlotsEntityRepositoryIntegrationTests {

    private BlockedSlotsRepository blockedSlotsRepository;

    @Autowired
    public void setBlockedSlotsRepository(BlockedSlotsRepository blockedSlotsRepository) {
        this.blockedSlotsRepository = blockedSlotsRepository;
    }

    @BeforeEach
    void clearDatabase() {
        blockedSlotsRepository.deleteAll();
    }


    @Test
    public void testBlockedSlotCanBeCreatedAndRecalled() {
        BlockedSlotsEntity blockedSlotEntity = TestDataUtil.createBlockedSlotA();
        blockedSlotsRepository.save(blockedSlotEntity);
        Optional<BlockedSlotsEntity> result = blockedSlotsRepository.findById(blockedSlotEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(blockedSlotEntity);
    }

    @Test
    public void testMultipleBlockedSlotsCanBeCreatedAndRecalled() {
        BlockedSlotsEntity blockedSlotEntityA = TestDataUtil.createBlockedSlotA();
        BlockedSlotsEntity blockedSlotEntityB = TestDataUtil.createBlockedSlotB();
        BlockedSlotsEntity blockedSlotEntityC = TestDataUtil.createBlockedSlotC();

        blockedSlotsRepository.save(blockedSlotEntityA);
        blockedSlotsRepository.save(blockedSlotEntityB);
        blockedSlotsRepository.save(blockedSlotEntityC);

        Iterable<BlockedSlotsEntity> result = blockedSlotsRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .contains(blockedSlotEntityA, blockedSlotEntityB, blockedSlotEntityC);
    }

    @Test
    public void testBlockedSlotCanBeUpdatedAndRecalled() {
        BlockedSlotsEntity blockedSlotEntity = TestDataUtil.createBlockedSlotA();
        blockedSlotsRepository.save(blockedSlotEntity);

        blockedSlotEntity.setSlotDate(LocalDate.parse("2025-07-12"));
        blockedSlotsRepository.save(blockedSlotEntity);

        Optional<BlockedSlotsEntity> result = blockedSlotsRepository.findById(blockedSlotEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(blockedSlotEntity);
    }

    @Test
    public void testBlockedSlotCanBeDeleted() {
        BlockedSlotsEntity blockedSlotEntity = TestDataUtil.createBlockedSlotA();
        blockedSlotsRepository.save(blockedSlotEntity);
        blockedSlotsRepository.delete(blockedSlotEntity);
        Optional<BlockedSlotsEntity> result = blockedSlotsRepository.findById(blockedSlotEntity.getId());
        assertThat(result).isEmpty();
    }
}
