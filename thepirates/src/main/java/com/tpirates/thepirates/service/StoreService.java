package com.tpirates.thepirates.service;

import com.tpirates.thepirates.dto.request.BusinessTimeDto;
import com.tpirates.thepirates.dto.request.HolidayRequestDto;
import com.tpirates.thepirates.dto.request.StoreRequestDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import com.tpirates.thepirates.model.BusinessTime;
import com.tpirates.thepirates.model.Store;
import com.tpirates.thepirates.model.WeekDay;
import com.tpirates.thepirates.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDetailDto findById(Long id) {
        Store store = storeRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);

        return Store.createStoreDetailDto(store);
    }

    public List<StoreDto> findAll() {
        return storeRepository
                .findAll()
                .stream()
                .map(Store::createStoreDto)
                .collect(Collectors.toList());
    }

    public void addStore(StoreRequestDto storeRequestDto) {
        Store store = new Store(storeRequestDto.getName(), storeRequestDto.getOwner(), storeRequestDto.getDescription(), storeRequestDto.getLevel(), storeRequestDto.getAddress(), storeRequestDto.getPhone());
        Map<Long, BusinessTime> businessTimes = storeRequestDto
                .getBusinessTimes()
                .stream()
                .map(BusinessTimeDto::createBusinessTime)
                .collect(Collectors.toMap(BusinessTime::getId, businessTime -> businessTime)
                );

        store.setBusinessTimes(businessTimes);
        storeRepository.save(store);
    }

}
