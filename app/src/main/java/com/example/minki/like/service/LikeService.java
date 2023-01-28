package com.example.minki.like.service;

import com.example.minki.like.model.vo.LikeRequestVo;

public interface LikeService {
//    String likeBoard(Long storeId);


    int countLike(Long storeId);

    int saveLike(LikeRequestVo likeRequestVo);

    //사용자가 누른 좋아요 갯수 조회
    Long userLikeCount(Long userId);
}
