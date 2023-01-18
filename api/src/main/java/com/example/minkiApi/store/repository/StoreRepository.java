package com.example.minkiApi.store.repository;

import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findAllByOrderByIdDesc();

    @Query(value = "select MAX(store_id) from store_entity", nativeQuery = true)
    Long getMaxStoreId();
}
