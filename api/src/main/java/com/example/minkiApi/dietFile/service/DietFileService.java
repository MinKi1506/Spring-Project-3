package com.example.minkiApi.dietFile.service;

import com.example.minkiApi.dietFile.model.entity.DietFileEntity;
import com.example.minkiApi.dietFile.model.vo.DietMultipartVo;

import java.util.List;

public interface DietFileService {

    String addDietFile(DietMultipartVo dietMultipartVo);

    int deleteDietFile(String fileName);

    List<DietFileEntity> getDietFileListByDietId(Long dietId);

}
