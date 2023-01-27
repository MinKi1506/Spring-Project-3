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

    Long getMaxStoreId();
}
