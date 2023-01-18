package com.example.minkiApi.storeFile.service;

import com.example.minkiApi.storeFile.model.Vo.MultipartVo;
import com.example.minkiApi.storeFile.model.entity.StoreFileEntity;

import java.util.List;

public interface StoreFileService {

    String addStoreFile(MultipartVo multipartVo);

    List<StoreFileEntity> getStoreFileListByStoreId(Long storeId);


}
