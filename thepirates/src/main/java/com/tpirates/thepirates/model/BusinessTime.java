package com.tpirates.thepirates.model;

import com.tpirates.thepirates.dto.response.BusinessDayDto;
import org.springframework.data.annotation.Id;

import java.util.List;

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

    public Integer getDay() {
        return day;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public static BusinessDayDto createBusinessDayDto(BusinessTime businessTime, List<Holiday> holidays) {
        return new BusinessDayDto(WeekDay.findDayByIndex(businessTime.day),
                businessTime.open, businessTime.close,
                Status.getStatusByTime(businessTime.open, businessTime.close, holidays));
    }

}
