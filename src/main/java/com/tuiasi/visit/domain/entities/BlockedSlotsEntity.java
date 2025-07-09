package com.tuiasi.visit.domain.entities;

import jakarta.persistence.*;
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
@Entity
@Table(name = "blocked_slots")
public class BlockedSlotsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = true, length = 100)
    private String reason;
}

