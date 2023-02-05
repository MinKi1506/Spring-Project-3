package com.example.minki.like.service.impl;

import com.example.minki.like.model.vo.LikeRequestVo;
import com.example.minki.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    @Autowired
    private final WebClient webClient;

    @Override
    public int countLike(Long storeId) {
        int response = webClient.get()
                .uri("/store/"+storeId+"/like")
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
    }

    //맛집 포스팅 좋아요
    @Override
    @Transactional
    public int saveLike(LikeRequestVo likeRequestVo) {
        int response;
        response = webClient.post()
                .uri("/like")
                .bodyValue(likeRequestVo)
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
    }

    //사용자가 누른 좋아요 갯수 조회
    @Override
    public Long userLikeCount(Long userId) {
        Long response;
        response = webClient.get()
                .uri("/like/"+userId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }

}
