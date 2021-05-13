package com.tpirates.thepirates.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessDayDto {
    @JsonProperty
    private String day;

    @JsonProperty
    private String open;

    @JsonProperty
    private String close;

    @JsonProperty
    private String status;

    public BusinessDayDto(String day, String open, String close, String status) {
        this.day = day;
        this.open = open;
        this.close = close;
        this.status = status;
    }
}
