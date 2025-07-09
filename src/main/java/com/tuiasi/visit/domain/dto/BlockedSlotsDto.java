package com.tuiasi.visit.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockedSlotsDto {
    private Integer id;

    private Date date;

    private Time startTime;

    private Time endTime;

    private String reason;
}
