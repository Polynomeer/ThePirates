package com.tpirates.thepirates.controller;

import com.tpirates.thepirates.dto.request.HolidayRequestDto;
import com.tpirates.thepirates.dto.request.StoreRequestDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import com.tpirates.thepirates.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    public static final LocalDateTime currentDateTime = LocalDateTime.now();

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDto> getStores() {
        return storeService.findAll();
    }

    @GetMapping("/{id}")
    public StoreDetailDto getStore(@PathVariable Long id) {
        return storeService.findById(id);
    }

    @PostMapping
    public void addStore(@RequestBody StoreRequestDto storeRequestDto) {
        storeService.addStore(storeRequestDto);
    }

    @PostMapping("/{id}")
    public void addHoliday(@PathVariable Long id, @RequestBody HolidayRequestDto holidayRequestDto) {
        storeService.addHoliday(id, holidayRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteById(id);
    }
}
