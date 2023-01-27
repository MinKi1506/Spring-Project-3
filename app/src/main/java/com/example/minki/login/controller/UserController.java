package com.example.minki.login.controller;

import com.example.minki.login.model.entity.UserEntity;
import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;
import com.example.minki.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/getUserName/{userId}")
    public String getUserName(@PathVariable Long userId){
        String response;
        response = userService.getUserName(userId);
        return response;
    }

}
