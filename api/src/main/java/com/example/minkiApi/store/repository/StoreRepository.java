package com.example.minkiApi.store.repository;

import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    //아이디 내림차순으로 전부 가져오기
    List<StoreEntity> findAllByOrderByIdDesc();

    //맛집 포스팅 Id 최댓값 가져오기
    @Query(value = "select MAX(store_id) from store_entity", nativeQuery = true)
    Long getMaxStoreId();

    //포스팅 제목으로 리스트 조회
    List<StoreEntity> findByTitleContaining(String title);

    //가게 상호명으로 리스트 조회
    List<StoreEntity> findByStoreNameContaining(String storeName);

    //포스팅 본문 내용으로 리스트 조회
    List<StoreEntity> findByContentContaining(String content);

    //최다 추천(Best) 맛집
    @Query(value = "select * from store_entity where like_count = (select MAX(like_count) from store_entity", nativeQuery = true)
    StoreEntity findBestStore();

    //최다 추천(Best) 맛집
    StoreEntity findTop1ByOrderByLikeCountDesc();

    //유저가 업로드한 맛집 포스팅 갯수
    Long countByWriterId(Long userId);


}
