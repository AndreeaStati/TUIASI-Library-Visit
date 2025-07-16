package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.repositories.BlockedSlotsRepository;
import com.tuiasi.visit.services.BlockedSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BlockedSlotsServiceImpl implements BlockedSlotsService {

    private BlockedSlotsRepository blockedSlotsRepository;

    @Autowired
    public void setBlockedSlotsRepository(BlockedSlotsRepository blockedSlotsRepository) {
        this.blockedSlotsRepository = blockedSlotsRepository;
    }

    @Override
    public BlockedSlotsEntity createBlockedSlot(BlockedSlotsEntity blockedSlotsEntity) {
        return blockedSlotsRepository.save(blockedSlotsEntity);
    }

    @Override
    public List<BlockedSlotsEntity> findAll() {
        return StreamSupport.stream(blockedSlotsRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BlockedSlotsEntity> findOne(Integer id) {
        return blockedSlotsRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return blockedSlotsRepository.existsById(id);
    }

    @Override
    public BlockedSlotsEntity save(BlockedSlotsEntity blockedSlotEntity) {
        return blockedSlotsRepository.save(blockedSlotEntity);
    }

    @Override
    public BlockedSlotsEntity partialUpdateBlockedSlot(Integer id, BlockedSlotsEntity blockedSlotEntity) {
        blockedSlotEntity.setId(id);

        return blockedSlotsRepository.findById(id).map(existingBlockedSlot -> {
            Optional.ofNullable(blockedSlotEntity.getSlotDate()).ifPresent(existingBlockedSlot::setSlotDate);
            Optional.ofNullable(blockedSlotEntity.getStartTime()).ifPresent(existingBlockedSlot::setStartTime);
            Optional.ofNullable(blockedSlotEntity.getEndTime()).ifPresent(existingBlockedSlot::setEndTime);
            Optional.ofNullable(blockedSlotEntity.getReason()).ifPresent(existingBlockedSlot::setReason);
            return blockedSlotsRepository.save(existingBlockedSlot);
        }).orElseThrow(() -> new RuntimeException("Blocked slot does not exist"));
    }

    @Override
    public void delete(Integer id) {
        blockedSlotsRepository.deleteById(id);
    }


}
