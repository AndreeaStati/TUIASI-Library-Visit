package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.repositories.BlockedSlotsRepository;
import com.tuiasi.visit.services.BlockedSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
