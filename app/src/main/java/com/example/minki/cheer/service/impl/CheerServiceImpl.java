package com.example.minki.cheer.service.impl;


import com.example.minki.cheer.model.entity.CheerEntity;
import com.example.minki.cheer.model.vo.CheerRequestVo;
import com.example.minki.cheer.service.CheerService;
import com.example.minki.diet.model.entity.DietEntity;
import com.example.minki.login.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheerServiceImpl implements CheerService {

    @Autowired
    private final WebClient webClient;

    @Override
    public int countCheer(Long dietId, Long userId) {
        int response = webClient.get()
                .uri("/diet/"+dietId+"/cheer")
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
    }

    //다이어 포스팅 응원해요
    @Override
    @Transactional
    public int saveCheer(CheerRequestVo cheerRequestVo) {
        int response;
        response = webClient.post()
                .uri("/cheer")
                .bodyValue(cheerRequestVo)
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
    }


    //유저가 누른 응원해요 갯수 조회
    @Override
    public Long userCheerCount(Long userId) {
        Long response;
        response = webClient.get()
                .uri("/cheer/"+userId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }
}
