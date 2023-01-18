package com.example.minkiApi.storeFile.service.impl;

import com.example.minkiApi.storeFile.model.Vo.MultipartVo;
import com.example.minkiApi.storeFile.model.entity.StoreFileEntity;
import com.example.minkiApi.storeFile.repository.StoreFileRepository;
import com.example.minkiApi.storeFile.service.StoreFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFileServiceImpl implements StoreFileService {

    @Autowired
    private final StoreFileRepository storeFileRepository;

    @Override
    public String addStoreFile(MultipartVo multipartVo) {
        String result;
        StoreFileEntity storeFileEntity = storeFileRepository.save(new StoreFileEntity(multipartVo));
        if(null != storeFileEntity.getId() && !"".equals(storeFileEntity.getId())){
            result="success";
        }else {
            result = "fail";
        }
        return result;
    }

    //가게 id로 파일 리스트 찾기
    @Override
    public List<StoreFileEntity> getStoreFileListByStoreId(Long storeId) {
        List<StoreFileEntity> storeFileList = storeFileRepository.findAllByStoreId(storeId);
        return storeFileList;
    }
}
