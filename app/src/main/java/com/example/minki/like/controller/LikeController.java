package com.example.minki.like.controller;


import com.example.minki.like.model.vo.LikeRequestVo;
import com.example.minki.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeService;

    //좋아요
    @PostMapping("/like")
    public int onLike(@RequestBody LikeRequestVo likeRequestVo) {
        int response = likeService.saveLike(likeRequestVo);
        return response; //response값이 0이면 성공적으로 삭제한 것이고, 1이면 성공적으로 좋아요를 누른 것이다.
    }

    //유저가 누른 좋아요 갯수
    @GetMapping("/like/{userId}")
    public Long getCountLikeByUserId(@PathVariable Long userId) {
        Long response;
        response = likeService.userLikeCount(userId);
        return  response;
    }

}
