package com.example.minkiApi.photoFile.repository;

import com.example.minkiApi.photoFile.model.entity.StoreFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreFileRepository extends CrudRepository<StoreFile, Long> {
    StoreFile save(StoreFile storeFile);

    List<StoreFile> findAllByStoreId(Long storeId);
}
