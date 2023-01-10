package com.example.minkiApi.login.controller;


import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;
import com.example.minkiApi.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    //회원가입
    @PostMapping("/signUp")
    public String signUp(@RequestBody UserSignUpVo userSignUpVo) throws Exception{
        String response;
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

}
