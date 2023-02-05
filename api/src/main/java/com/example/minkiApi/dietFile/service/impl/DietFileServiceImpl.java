package com.example.minkiApi.dietFile.service.impl;

import com.example.minkiApi.dietFile.model.entity.DietFileEntity;
import com.example.minkiApi.dietFile.model.vo.DietMultipartVo;
import com.example.minkiApi.dietFile.repository.DietFileRepository;
import com.example.minkiApi.dietFile.service.DietFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietFileServiceImpl implements DietFileService {

    @Autowired
    private final DietFileRepository dietFileRepository;


    //db에 dietFile 저장
    @Override
    public String addDietFile(DietMultipartVo dietMultipartVo) {
        String response;
        DietFileEntity dietFileEntity = dietFileRepository.save(new DietFileEntity(dietMultipartVo));
        if(null != dietFileEntity.getId() && !"".equals(dietFileEntity.getId())){
            response="success";
        }else {
            response = "fail";
        }
        return response;
    }

    
    //db에 dietFile 삭제
    @Override
    @Transactional
    public int deleteDietFile(String fileName) {
        int response;
        try{
            dietFileRepository.deleteByDietFile(fileName);
            response = 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            response = 0;
        }
        return response;
    }

    
    //다이어트 id로 파일 리스트 찾기
    @Override
    public List<DietFileEntity> getDietFileListByDietId(Long dietId) {
        List<DietFileEntity> dietFileList = dietFileRepository.findAllByDietId(dietId);
        return dietFileList;
    }
}
