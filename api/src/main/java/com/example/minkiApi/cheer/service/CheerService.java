package com.example.minkiApi.cheer.service;

public interface CheerService {
//    String cheerBoard(Long storeId);

    int countCheer(Long dietId, Long userId);

    int saveCheer(Long dietId, Long userId);

    Long userCheerCount(Long userId);

}
