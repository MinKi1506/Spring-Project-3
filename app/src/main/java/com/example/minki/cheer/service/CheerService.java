package com.example.minki.cheer.service;

import com.example.minki.cheer.model.vo.CheerRequestVo;

public interface CheerService {
//    String cheerBoard(Long storeId);

    int countCheer(Long dietId, Long userId);

    int saveCheer(CheerRequestVo cheerRequestVo);

    Long userCheerCount(Long userId);

}
