package com.tpirates.thepirates.model;

import org.springframework.data.annotation.Id;

public class Holiday {
    @Id
    private Long id;

    private String holiday;

    public Holiday(String holiday) {
        this.holiday = holiday;
    }
}
