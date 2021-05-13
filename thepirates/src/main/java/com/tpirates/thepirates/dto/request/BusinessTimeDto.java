package com.tpirates.thepirates.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessTimeDto {
    @JsonProperty
    private String day;

    @JsonProperty
    private String open;

    @JsonProperty
    private String close;

    public BusinessTimeDto(String day, String open, String close) {
        this.day = day;
        this.open = open;
        this.close = close;
    }

    @Override
    public String toString() {
        return "BusinessTimeDto{" +
                "day='" + day + '\'' +
                ", open='" + open + '\'' +
                ", close='" + close + '\'' +
                '}';
    }
}
