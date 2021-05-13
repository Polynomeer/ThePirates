package com.tpirates.thepirates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreDto {
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private Integer level;

    @JsonProperty
    private String businessStatus;

    public StoreDto(String name, String description, Integer level, String businessStatus) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.businessStatus = businessStatus;
    }
}
