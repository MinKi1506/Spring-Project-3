package com.example.minkiApi.like.repository;

import com.example.minkiApi.like.model.entity.LikeEntity;
import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByStoreEntity_IdAndUserEntity_Id(Long storeId, Long userId);

    void deleteByStoreEntity_IdAndUserEntity_Id(Long storeId, Long userId);

//    Optional<LikeEntity> findByStoreEntityIdAndUserEntityId(Long storeId, Long userId);
//
//    void deleteByStoreEntityIdAndUserEntityId(Long storeId, Long userId);

}
