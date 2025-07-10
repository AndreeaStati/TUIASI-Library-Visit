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


}
