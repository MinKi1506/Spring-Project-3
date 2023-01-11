package com.example.minki.like.controller;


import com.example.minki.like.model.vo.LikeRequestVo;
import com.example.minki.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeService;

    //좋아요
    @PostMapping("/like")
    public int onLike(@RequestBody LikeRequestVo likeRequestVo) {
        int result = likeService.saveLike(likeRequestVo);
        return result; //result값이 0이면 성공적으로 삭제한 것이고, 1이면 성공적으로 좋아요를 누른 것이다.
    }


}
