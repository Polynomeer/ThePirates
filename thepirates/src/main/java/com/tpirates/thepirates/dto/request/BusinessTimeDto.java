package com.tpirates.thepirates.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpirates.thepirates.model.BusinessTime;
import com.tpirates.thepirates.model.WeekDay;

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

    public String getDay() {
        return day;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public static BusinessTime createBusinessTime(BusinessTimeDto businessTimeDto) {
        return new BusinessTime(
                WeekDay.findIndexByDay(businessTimeDto.getDay()),
                businessTimeDto.getOpen(),
                businessTimeDto.getClose()
        );
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
