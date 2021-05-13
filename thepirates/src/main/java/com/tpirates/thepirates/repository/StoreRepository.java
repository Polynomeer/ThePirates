package com.tpirates.thepirates.repository;

import com.tpirates.thepirates.model.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store, Long> {

    @Override
    List<Store> findAll();
}
