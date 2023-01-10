package com.example.minkiApi.store.service;

import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    //포스팅 추가
    String addStore(AddStoreVo addStoreVo);

    //포스팅 수정
    Optional<StoreEntity> updateStore(Long id, AddStoreVo addStoreVo);

    //포스팅 삭제
    String deleteStore(Long id);

    Optional<StoreEntity> getStore(Long id);

    List<StoreEntity> getStoreList();
}
