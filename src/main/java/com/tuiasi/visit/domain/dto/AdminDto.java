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
public class AdminDto {


    private Integer id;

    private String username;

    @JsonProperty("password_hash")
    private String passwordHash;

    private String email;
}
