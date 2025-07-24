package com.tuiasi.visit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.services.BlockedSlotsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.tuiasi.visit.TestDataUtil;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BlockedSlotsControllerIntegrationTests {

    private BlockedSlotsService blockedSlotsService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public BlockedSlotsControllerIntegrationTests(MockMvc mockMvc, BlockedSlotsService blockedSlotsService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.blockedSlotsService = blockedSlotsService;
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper = objectMapper;
    }
/*
    @Test
    public void testThatCreateBlockedSlotSuccessfullyReturnsHttp201Created() throws Exception {
        BlockedSlotsEntity testBlockedSlotA = TestDataUtil.createTestBlockedSlotEntityA();
        testBlockedSlotA.setId(null);
        String blockedSlotJson = objectMapper.writeValueAsString(testBlockedSlotA);
        System.out.println(blockedSlotJson);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/blocked-slots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(blockedSlotJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
*/

    @Test
    public void testThatCreateBlockedSlotSuccessfullyReturnsSavedBlockedSlot() throws Exception {
        BlockedSlotsDto testBlockedSlotA = TestDataUtil.createTestBlockedSlotDtoA();
        testBlockedSlotA.setId(null);
        String blockedSlotJson = objectMapper.writeValueAsString(testBlockedSlotA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/blocked-slots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(blockedSlotJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.slot_date").value("2025-07-13")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.start_time").value("13:00:00")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.end_time").value("14:00:00")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.reason").value("Probleme tehnice")
        );
    }

    @Test
    public void testThatListBlockedSlotsReturnsHttpStatus200() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/blocked-slots")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }



}

