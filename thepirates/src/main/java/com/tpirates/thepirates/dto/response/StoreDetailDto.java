package com.tpirates.thepirates.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StoreDetailDto {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private Integer level;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phone;

    @JsonProperty
    private List<BusinessDayDto> businessDays;

    public StoreDetailDto(Long id, String name, String description, Integer level, String address, String phone, List<BusinessDayDto> businessDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
        this.businessDays = businessDays;
    }
}
