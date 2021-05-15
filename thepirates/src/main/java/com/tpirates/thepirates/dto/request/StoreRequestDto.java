package com.tpirates.thepirates.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StoreRequestDto {
    @JsonProperty
    private String name;

    @JsonProperty
    private String owner;

    @JsonProperty
    private String description;

    @JsonProperty
    private Integer level;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phone;

    @JsonProperty
    private List<BusinessTimeDto> businessTimes;

    public StoreRequestDto(String name, String owner, String description, Integer level, String address, String phone, List<BusinessTimeDto> businessTimes) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
        this.businessTimes = businessTimes;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLevel() {
        return level;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<BusinessTimeDto> getBusinessTimes() {
        return businessTimes;
    }

    @Override
    public String toString() {
        return "StoreRequestDto{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", businessTimes=" + businessTimes +
                '}';
    }
}
