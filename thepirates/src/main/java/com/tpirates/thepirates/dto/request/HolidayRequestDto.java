package com.tpirates.thepirates.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HolidayRequestDto {
    @JsonProperty
    private Long id;

    @JsonProperty
    private List<String> holidays;

    public HolidayRequestDto(Long id, List<String> holidays) {
        this.id = id;
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "HolidayRequestDto{" +
                "id=" + id +
                ", holidays=" + holidays +
                '}';
    }
}
