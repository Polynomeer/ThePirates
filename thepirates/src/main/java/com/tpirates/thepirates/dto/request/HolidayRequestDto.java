package com.tpirates.thepirates.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpirates.thepirates.model.Holiday;

import java.util.List;
import java.util.stream.Collectors;

public class HolidayRequestDto {
    @JsonProperty
    private Long id;

    @JsonProperty
    private List<String> holidays;

    public HolidayRequestDto(Long id, List<String> holidays) {
        this.id = id;
        this.holidays = holidays;
    }

    public List<String> getHolidays() {
        return holidays;
    }

    public static List<Holiday> createHolidays(HolidayRequestDto holidayRequestDto){
        return holidayRequestDto.holidays
                .stream()
                .map(Holiday::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "HolidayRequestDto{" +
                "id=" + id +
                ", holidays=" + holidays +
                '}';
    }
}
