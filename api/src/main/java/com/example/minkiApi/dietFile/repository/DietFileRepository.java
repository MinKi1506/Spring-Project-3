package com.example.minkiApi.dietFile.repository;

import com.example.minkiApi.dietFile.model.entity.DietFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietFileRepository extends JpaRepository<DietFileEntity, Long> {

    DietFileEntity save(DietFileEntity dietFileEntity);

    void deleteByDietFile(String fileName);

    List<DietFileEntity> findAllByDietId(Long dietId);
    
}
