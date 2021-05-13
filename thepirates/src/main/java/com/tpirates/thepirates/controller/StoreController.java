package com.tpirates.thepirates.controller;

import com.tpirates.thepirates.dto.StoreDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @GetMapping
    public List<StoreDto> getStores() {
        List<StoreDto> storeDtoList = new ArrayList<>();
        StoreDto storeDto1 = new StoreDto("해적수산", "노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집", 1, "OPEN");
        StoreDto storeDto2 = new StoreDto("인어수산", "인천소래포구 종합어시장 갑각류센터 인어수산", 2, "HOLIDAY");
        storeDtoList.add(storeDto1);
        storeDtoList.add(storeDto2);
        return storeDtoList;
    }

}
