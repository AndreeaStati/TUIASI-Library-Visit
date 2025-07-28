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

@CrossOrigin(origins = "http://localhost:5173")
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

     @PutMapping(path = "/blocked-slots/{id}")
    public ResponseEntity<BlockedSlotsDto> fullUpdateBlockedSlot(
            @PathVariable("id") Integer id,
            @RequestBody BlockedSlotsDto blockedSlotDto) {

        if(!blockedSlotsService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blockedSlotDto.setId(id);
        BlockedSlotsEntity blockedSlotEntity = blockedSlotsMapper.mapFrom(blockedSlotDto);
        BlockedSlotsEntity savedBlockedSlotEntity = blockedSlotsService.save(blockedSlotEntity);
        return new ResponseEntity<>(
                blockedSlotsMapper.mapTo(savedBlockedSlotEntity),
                HttpStatus.OK);
    }


    @PatchMapping(path = "/blocked-slots/{id}")
    public ResponseEntity<BlockedSlotsDto> partialUpdateBlockedSlot(
            @PathVariable("id") Integer id,
            @RequestBody BlockedSlotsDto blockedSlotDto
    ) {
        if(!blockedSlotsService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BlockedSlotsEntity blockedSlotEntity = blockedSlotsMapper.mapFrom(blockedSlotDto);
        BlockedSlotsEntity updatedBlockedSlot = blockedSlotsService.partialUpdateBlockedSlot(id, blockedSlotEntity);
        return new ResponseEntity<>(
                blockedSlotsMapper.mapTo(updatedBlockedSlot),
                HttpStatus.OK);
    }


    @DeleteMapping(path = "/blocked-slots/{id}")
    public ResponseEntity deleteBlockedSlot(@PathVariable("id") Integer id) {
        blockedSlotsService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}


