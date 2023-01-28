package com.example.minki.store.service;

import com.example.minki.store.model.entity.StoreEntity;
import com.example.minki.store.model.vo.AddStoreVo;

import java.util.List;

public interface StoreService {
    //포스팅 추가
    String addStore(AddStoreVo addStoreVo);

//    //포스팅 수정
    String updateStore(Long id, AddStoreVo addStoreVo);

//    //포스팅 삭제
    String deleteStore(Long id);

//    포스팅 1개 조회
    StoreEntity getStore(Long id);

//    포스팅 리스트 조회
    List<StoreEntity> getStoreList();

    //최대 storeId 조회
    Long getMaxStoreId();

    //제목으로 리스트 검색
    List<StoreEntity> searchStoreByTitle(String title);


    //가게명으로 리스트 검색
    List<StoreEntity> searchStoreByStoreName(String storeName);

    //본문 내용으로 리스트 검색
    List<StoreEntity> searchStoreByContent(String content);

    //최다 추천(Best) 맛집
    StoreEntity getBestStore();

    //내가올린 맛집 포스팅 갯수
    Long getCountByUserId(Long userId);
}
