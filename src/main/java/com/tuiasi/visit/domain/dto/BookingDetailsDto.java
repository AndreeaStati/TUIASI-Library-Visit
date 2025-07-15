package com.tuiasi.visit.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDetailsDto {
    private BookingDto booking;
    private CategoryDto category;

    @JsonProperty("number_of_users")
    private Integer numberOfUsers;
}
