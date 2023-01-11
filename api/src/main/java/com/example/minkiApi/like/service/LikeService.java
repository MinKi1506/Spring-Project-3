package com.example.minkiApi.like.service;

import com.example.minkiApi.like.model.vo.LikeRequestVo;

public interface LikeService {
//    String likeBoard(Long storeId);


    int findLike(Long storeId, Long userId);

    int saveLike(Long storeId, Long userId);
}
