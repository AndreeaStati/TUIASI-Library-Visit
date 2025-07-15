package com.tuiasi.visit.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private Integer id;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    private Integer amount;

    private BookingDto booking;
}
