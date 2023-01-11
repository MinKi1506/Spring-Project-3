package com.example.minkiApi.like.controller;

import com.example.minkiApi.like.model.vo.LikeRequestVo;
import com.example.minkiApi.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeService;

    //좋아요&좋아요 취소
    @PostMapping("/like")
    public int onLike(@RequestBody LikeRequestVo likeRequestVo) {
        Long storeId = likeRequestVo.getStoreId();
        Long userId = likeRequestVo.getUserId();
        int result = likeService.saveLike(storeId,userId);
        return result;
    }

//    @GetMapping


}
