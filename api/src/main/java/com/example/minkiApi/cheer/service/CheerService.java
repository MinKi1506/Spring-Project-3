package com.example.minkiApi.cheer.service;

public interface CheerService {

    int countCheer(Long dietId, Long userId);

    int saveCheer(Long dietId, Long userId);

    Long userCheerCount(Long userId);

}
