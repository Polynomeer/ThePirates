package com.tpirates.thepirates.model;

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
}
