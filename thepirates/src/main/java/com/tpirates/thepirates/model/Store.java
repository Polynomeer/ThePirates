package com.tpirates.thepirates.model;

import com.tpirates.thepirates.controller.StoreController;
import com.tpirates.thepirates.dto.response.BusinessDayDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.*;
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

    @MappedCollection(idColumn = "STORE_ID")
    private Set<BusinessTime> businessTimes = new HashSet<>();


    @MappedCollection(idColumn = "STORE_ID")
    private Set<Holiday> holidays = new HashSet<>();

    public Store(String name, String owner, String description, Integer level, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
    }

    public static StoreDto createStoreDto(Store store) {
        return new StoreDto(store.name, store.description, store.level,
                Status.getStatusByTime(
                        new ArrayList<>(store.businessTimes),
                        new ArrayList<>(store.holidays),
                        StoreController.currentDateTime
                )
        );
    }

    public static StoreDetailDto createStoreDetailDto(Store store) {
        List<Holiday> holidays = new ArrayList<>(store.holidays);

        List<BusinessDayDto> businessDays = new ArrayList<>();
        String today = StoreController.currentDateTime.getDayOfWeek().toString();
        int todayIndex = WeekDay.findIndexByDay(today);

        List<BusinessTime> businessTimes = new ArrayList<>(store.businessTimes);
        businessTimes.sort(Comparator.comparing(BusinessTime::getDay));

        LocalDateTime currentDateTime = StoreController.currentDateTime;

        for (int i = todayIndex; i < todayIndex + 3; i++) {
            int dayIndex = WeekDay.findIndexByDay(currentDateTime.getDayOfWeek().toString());
            if (dayIndex < businessTimes.size()) {
                businessDays.add(BusinessTime.createBusinessDayDto(businessTimes.get(dayIndex), holidays, currentDateTime));
            }
            currentDateTime = currentDateTime.plusDays(1);
        }

        return new StoreDetailDto(store.id, store.name, store.description, store.level, store.address, store.phone, businessDays);
    }

    public void setBusinessTimes(Set<BusinessTime> businessTimes) {
        this.businessTimes = businessTimes;
    }

    public void setHolidays(Set<Holiday> holidays) {
        this.holidays = holidays;
    }
}
