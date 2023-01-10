package com.example.minkiApi.store.repository;

import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    List<StoreEntity> findAllByOrderByIdDesc();
}
