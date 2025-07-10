package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;

import java.util.List;
import java.util.Optional;

public interface BlockedSlotsService {
    BlockedSlotsEntity createBlockedSlot(BlockedSlotsEntity blockedSlotsEntity);

    List<BlockedSlotsEntity> findAll();

    Optional<BlockedSlotsEntity> findOne(Integer id);
}
