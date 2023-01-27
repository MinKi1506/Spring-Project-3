package com.example.minkiApi.login.controller;


import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;
import com.example.minkiApi.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    //회원가입
    @PostMapping("/signUp")
    public Long signUp(@RequestBody UserSignUpVo userSignUpVo) throws Exception{
        Long response;
        response = userService.signUp(userSignUpVo);
        return response;
    }

    //로그인
    @PostMapping("/signIn")
    public Long signIn(@RequestBody UserSignInVo userSignInVo){
        Long response;
        response = userService.signIn(userSignInVo);
        return response;
    }

    //유저 이름 조회
    @GetMapping("/getUserName/{userId}")
    public String getUserName(@PathVariable Long userId){
        String response;
        response = userService.getUserName(userId);
        return response;
    }
}
