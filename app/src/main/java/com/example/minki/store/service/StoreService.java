package com.example.minki.store.service;

import com.example.minki.store.model.entity.StoreEntity;
import com.example.minki.store.model.vo.AddStoreVo;

import java.util.Optional;

public interface StoreService {
    //포스팅 추가
    String addStore(AddStoreVo addStoreVo);

    //포스팅 수정
    Optional<StoreEntity> updateStore(Long id, AddStoreVo addStoreVo);

    //포스팅 삭제
    String deleteStore(Long id);
}
