package com.tpirates.thepirates.service;

import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import com.tpirates.thepirates.model.Store;
import com.tpirates.thepirates.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
