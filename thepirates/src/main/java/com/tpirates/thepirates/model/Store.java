package com.tpirates.thepirates.model;

import com.tpirates.thepirates.dto.response.BusinessDayDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store {
    @Id
    private Long id;

    private String name;
    private String owner;
    private String description;
    private Integer level;
    private String address;
    private String phone;

    @MappedCollection(idColumn = "STORE_ID", keyColumn = "ID")
    private Map<Long, BusinessTime> businessTimes = new HashMap<>();

    public Store(String name, String owner, String description, Integer level, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
    }

    public void setBusinessTimes(Map<Long, BusinessTime> businessTimes) {
        this.businessTimes = businessTimes;
    }

    public static StoreDto createStoreDto(Store store) {
        return new StoreDto(store.name, store.description, store.level, "OPEN");
    }

    public static StoreDetailDto createStoreDetailDto(Store store) {
        List<BusinessDayDto> businessDays = store.businessTimes
                .values()
                .stream()
                .map(BusinessTime::createBusinessDayDto)
                .collect(Collectors.toList());

        return new StoreDetailDto(store.id, store.name, store.description, store.level, store.address, store.phone, businessDays);
    }
}
