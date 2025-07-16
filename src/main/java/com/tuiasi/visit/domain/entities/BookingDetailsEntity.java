package com.tuiasi.visit.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookingDetailsEntity mappează relația many-to-many dintre bookings și categories,
 * cu o cheie compusă și câmp suplimentar: number_of_persons.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="booking_details")
public class BookingDetailsEntity {

    @EmbeddedId
    private BookingDetailsId id;

    @ManyToOne
    @MapsId("bookingId")
    @JoinColumn(name="booking_id")
    private BookingEntity booking;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name="category_id", columnDefinition = "TINYINT")
    private CategoryEntity category;

   @Column(name="number_of_users", columnDefinition = "DECIMAL(2)")
    private Integer numberOfUsers;
}
