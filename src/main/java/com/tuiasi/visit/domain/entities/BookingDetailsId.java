package com.tuiasi.visit.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookingDetailsId implements Serializable {

    private Integer bookingId;
    private Integer categoryId;
}
