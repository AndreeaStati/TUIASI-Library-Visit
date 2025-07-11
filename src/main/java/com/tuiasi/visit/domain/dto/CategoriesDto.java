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
public class CategoriesDto {

    private Integer id;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("price_per_person")
    private Double pricePerPerson;
}
