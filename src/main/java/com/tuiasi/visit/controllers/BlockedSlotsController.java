package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.BlockedSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockedSlotsController {
    private final BlockedSlotsService blockedSlotsService;
    private final Mapper<BlockedSlotsEntity, BlockedSlotsDto> blockedSlotsMapper;

    @Autowired
    public BlockedSlotsController(BlockedSlotsService blockedSlotsService, Mapper<BlockedSlotsEntity, BlockedSlotsDto> blockedSlotsMapper) {
        this.blockedSlotsService = blockedSlotsService;
        this.blockedSlotsMapper = blockedSlotsMapper;
    }
    @PostMapping(path = "/blocked-slots")
    public ResponseEntity<BlockedSlotsDto> createBlockedSlot(@RequestBody BlockedSlotsDto blockedSlot) {
        BlockedSlotsEntity blockedSlotsEntity = blockedSlotsMapper.mapFrom(blockedSlot);
        BlockedSlotsEntity savedBlockedSlotEntity = blockedSlotsService.createBlockedSlot(blockedSlotsEntity);
        BlockedSlotsDto response = blockedSlotsMapper.mapTo(savedBlockedSlotEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}


