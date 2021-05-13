package com.tpirates.thepirates.model;

import com.tpirates.thepirates.dto.response.StoreDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashMap;
import java.util.Map;

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

    public static StoreDto createStoreDto(Store store) {
        return new StoreDto(store.name, store.description, store.level, "OPEN");
    }
}
