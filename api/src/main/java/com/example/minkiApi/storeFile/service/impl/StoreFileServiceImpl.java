package com.example.minkiApi.storeFile.service.impl;

import com.example.minkiApi.storeFile.model.Vo.MultipartVo;
import com.example.minkiApi.storeFile.model.entity.StoreFileEntity;
import com.example.minkiApi.storeFile.repository.StoreFileRepository;
import com.example.minkiApi.storeFile.service.StoreFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFileServiceImpl implements StoreFileService {

    @Autowired
    private final StoreFileRepository storeFileRepository;

    //db에 storeFile 저장
    @Override
    public String addStoreFile(MultipartVo multipartVo) {
        String response;
        StoreFileEntity storeFileEntity = storeFileRepository.save(new StoreFileEntity(multipartVo));
        if(null != storeFileEntity.getId() && !"".equals(storeFileEntity.getId())){
            response="success";
        }else {
            response = "fail";
        }
        return response;
    }

    //db에 storeFile 삭제
    @Override
    @Transactional
    public int deleteStoreFile(String fileName) {
        int response;
        try{
            storeFileRepository.deleteByStoreFile(fileName);
            response = 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            response = 0;
        }
        return response;
    }

    //가게 id로 파일 리스트 찾기
    @Override
    public List<StoreFileEntity> getStoreFileListByStoreId(Long storeId) {
        List<StoreFileEntity> storeFileList = storeFileRepository.findAllByStoreId(storeId);
        return storeFileList;
    }
}
