package com.tuiasi.visit.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Integer id;

    @JsonProperty("booking_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @JsonProperty("total_price")
    private Integer totalPrice;

    private String status;

    private String details;

    private UserDto user;
}