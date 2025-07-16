package com.tuiasi.visit.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "admins")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT", name="admin_id")
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(name="password_hash")
    private String passwordHash;

    @Column(length = 100)
    private String email;
}
