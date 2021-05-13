package com.tpirates.thepirates.model;

import com.tpirates.thepirates.dto.response.BusinessDayDto;
import org.springframework.data.annotation.Id;

public class BusinessTime {
    @Id
    private Long id;

    private Integer day;
    private String open;
    private String close;

    public BusinessTime(Integer day, String open, String close) {
        this.day = day;
        this.open = open;
        this.close = close;
    }

    public static BusinessDayDto createBusinessDayDto(BusinessTime businessTime) {
        return new BusinessDayDto("Monday", businessTime.open, businessTime.close, "CLOSE");
    }
}
