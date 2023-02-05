package com.example.minkiApi.store.service;

import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    //포스팅 추가
    String addStore(AddStoreVo addStoreVo);

//    //포스팅 추가
//    StoreEntity addStore(AddStoreVo addStoreVo, MultipartHttpServletRequest multipartHttpServletRequest);

    //포스팅 수정
    Optional<StoreEntity> updateStore(Long id, AddStoreVo addStoreVo);

    //포스팅 삭제
    String deleteStore(Long id);

    //포스팅 1개 조회
    Optional<StoreEntity> getStore(Long id);

    //포스팅 리스트 조회
    List<StoreEntity> getStoreList();

    //최대 storeId 조회
    Long getMaxStoreId();

    //포스팅 제목으로 리스트 조회
    List<StoreEntity> searchStoreByTitle(String title);

    //가게 상호명으로 리스트 조회
    List<StoreEntity> searchStoreByStoreName(String storeName);

    //포스팅 본문 내용으로 리스트 조회
    List<StoreEntity> searchStoreByContent(String content);

    //최다 추천(Best) 맛집
    StoreEntity getBestStore();

    //내가올린 맛집 포스팅 갯수트
    Long getCountStoreByUserId(Long userId);
}
