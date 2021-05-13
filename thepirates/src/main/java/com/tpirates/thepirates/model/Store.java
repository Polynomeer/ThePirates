package com.tpirates.thepirates.model;

import org.springframework.data.annotation.Id;

public class Store {
    @Id
    private Long id;

    private String name;
    private String owner;
    private String description;
    private Integer level;
    private String address;
    private String phone;

    public Store(String name, String owner, String description, Integer level, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
    }
}
