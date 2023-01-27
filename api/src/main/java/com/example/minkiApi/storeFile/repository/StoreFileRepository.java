package com.example.minkiApi.storeFile.repository;

import com.example.minkiApi.storeFile.model.entity.StoreFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreFileRepository extends JpaRepository<StoreFileEntity, Long> {

    StoreFileEntity save(StoreFileEntity storeFileEntity);

    void deleteByStoreFile(String fileName);

    List<StoreFileEntity> findAllByStoreId(Long storeId);

}
