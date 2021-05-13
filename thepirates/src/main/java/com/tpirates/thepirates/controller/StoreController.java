package com.tpirates.thepirates.controller;

import com.tpirates.thepirates.dto.request.HolidayRequestDto;
import com.tpirates.thepirates.dto.request.StoreRequestDto;
import com.tpirates.thepirates.dto.response.BusinessDayDto;
import com.tpirates.thepirates.dto.response.StoreDetailDto;
import com.tpirates.thepirates.dto.response.StoreDto;
import com.tpirates.thepirates.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<BusinessDayDto> businessDayList = new ArrayList<>();
        BusinessDayDto businessDayDto1 = new BusinessDayDto("Wednesday", "09:00", "18:00", "CLOSE");
        BusinessDayDto businessDayDto2 = new BusinessDayDto("Thursday", "09:00", "23:00", "HOLIDAY");
        BusinessDayDto businessDayDto3 = new BusinessDayDto("Friday", "09:00", "23:00", "HOLIDAY");
        businessDayList.add(businessDayDto1);
        businessDayList.add(businessDayDto2);
        businessDayList.add(businessDayDto3);
        return new StoreDetailDto(1L, "인어수산", "인천소래포구 종합어시장 갑각류센터 인어수산", 2, "인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1 층 1 호", "010-1111-2222", businessDayList);
    }

    @PostMapping
    public void addStore(@RequestBody StoreRequestDto storeRequestDto){
        System.out.println(storeRequestDto);
    }

    @PostMapping("/{id}")
    public void addHoliday(@PathVariable Long id, @RequestBody HolidayRequestDto holidayRequestDto){
        System.out.println(holidayRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        System.out.println("Deleted store " + id);
    }
}
