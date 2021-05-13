package com.tpirates.thepirates.controller;

import com.tpirates.thepirates.dto.request.HolidayRequestDto;
import com.tpirates.thepirates.dto.request.StoreRequestDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import com.tpirates.thepirates.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
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
        System.out.println(storeRequestDto);
    }

    @PostMapping("/{id}")
    public void addHoliday(@PathVariable Long id, @RequestBody HolidayRequestDto holidayRequestDto) {
        System.out.println(holidayRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        System.out.println("Deleted store " + id);
    }
}
