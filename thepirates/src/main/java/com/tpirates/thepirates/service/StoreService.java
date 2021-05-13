package com.tpirates.thepirates.service;

import com.tpirates.thepirates.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
}
