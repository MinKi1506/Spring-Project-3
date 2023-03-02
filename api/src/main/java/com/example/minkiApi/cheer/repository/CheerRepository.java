package com.example.minkiApi.cheer.repository;

import com.example.minkiApi.cheer.model.entity.CheerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheerRepository extends JpaRepository<CheerEntity, Long> {

    Optional<CheerEntity> findByDietEntity_IdAndUserEntity_Id(Long dietId, Long userId);

    void deleteByDietEntity_IdAndUserEntity_Id(Long dietId, Long userId);

    Long countByUserEntity_Id(Long userId);

}
