package com.example.minki.cheer.controller;

import com.example.minki.cheer.model.vo.CheerRequestVo;
import com.example.minki.cheer.service.CheerService;
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

    //응원해요&응원해요 취소
    @PostMapping("/cheer")
    public int onCheer(@RequestBody CheerRequestVo cheerRequestVo) {
        int result = cheerService.saveCheer(cheerRequestVo);
        return result;  //response값이 0이면 성공적으로 삭제한 것이고, 1이면 성공적으로 좋아요를 누른 것이다.
    }

    //사용자가 누른 응원해요 갯수 조회
    @GetMapping("/cheer/{userId}")
    public Long getCountCheerByUserId(@PathVariable Long userId) {
        Long cheerCount;
        cheerCount = cheerService.userCheerCount(userId);
        return cheerCount;
    }
}
