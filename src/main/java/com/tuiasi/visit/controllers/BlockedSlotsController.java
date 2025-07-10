package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.BlockedSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BlockedSlotsController {
    private final BlockedSlotsService blockedSlotsService;
    private final Mapper<BlockedSlotsEntity, BlockedSlotsDto> blockedSlotsMapper;

    @Autowired
    public BlockedSlotsController(BlockedSlotsService blockedSlotsService, Mapper<BlockedSlotsEntity, BlockedSlotsDto> blockedSlotsMapper) {
        this.blockedSlotsService = blockedSlotsService;
        this.blockedSlotsMapper = blockedSlotsMapper;
    }

    @GetMapping(path = "/blocked-slots")
    public List<BlockedSlotsDto> listBlockedSlots() {
        List<BlockedSlotsEntity> blockedSlots = blockedSlotsService.findAll();
        return blockedSlots.stream()
                .map(blockedSlotsMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/blocked-slots/{id}")
    public ResponseEntity<BlockedSlotsDto> getBlockedSlot(@PathVariable("id") Integer id) {
        Optional<BlockedSlotsEntity> foundBlockedSlot = blockedSlotsService.findOne(id);
        return foundBlockedSlot.map(blockedSlotsEntity -> {
            BlockedSlotsDto blockedSlotsDto = blockedSlotsMapper.mapTo(blockedSlotsEntity);
            return new ResponseEntity<>(blockedSlotsDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/blocked-slots")
    public ResponseEntity<BlockedSlotsDto> createBlockedSlot(@RequestBody BlockedSlotsDto blockedSlot) {
        BlockedSlotsEntity blockedSlotsEntity = blockedSlotsMapper.mapFrom(blockedSlot);
        BlockedSlotsEntity savedBlockedSlotEntity = blockedSlotsService.createBlockedSlot(blockedSlotsEntity);
        BlockedSlotsDto response = blockedSlotsMapper.mapTo(savedBlockedSlotEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}


