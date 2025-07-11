package com.tuiasi.visit.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.ShiftLeft;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(length = 10)
    private String status;

    @Column(length = 150)
    private String details;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
