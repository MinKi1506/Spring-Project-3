package com.example.minkiApi.cheer.controller;

import com.example.minkiApi.cheer.model.vo.CheerRequestVo;
import com.example.minkiApi.cheer.service.CheerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CheerController {

    @Autowired
    CheerService cheerService;

    //응원해요 & 응원해요 취소
    @PostMapping("/cheer")
    public int onCheer(@RequestBody CheerRequestVo cheerRequestVo) {
        Long dietId = cheerRequestVo.getDietId();
        Long userId = cheerRequestVo.getUserId();
        int result = cheerService.saveCheer(dietId,userId);
        return result;
    }

    //사용자가 누른 좋아요 갯수 조회
    @GetMapping("/cheer/{userId}")
    public Long getCountCheerByUserId(@PathVariable Long userId) {
        Long cheerCount;
        cheerCount = cheerService.userCheerCount(userId);
        return cheerCount;
    }

}
