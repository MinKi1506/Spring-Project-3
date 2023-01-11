package com.example.minki.like.service;

import com.example.minki.like.model.vo.LikeRequestVo;

public interface LikeService {
//    String likeBoard(Long storeId);


    int countLike(Long storeId);

    int saveLike(LikeRequestVo likeRequestVo);
}
