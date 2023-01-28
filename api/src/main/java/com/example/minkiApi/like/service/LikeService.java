package com.example.minkiApi.like.service;

public interface LikeService {
//    String likeBoard(Long storeId);


    int countLike(Long storeId, Long userId);

    int saveLike(Long storeId, Long userId);

    Long userLikeCount(Long userId);

}
