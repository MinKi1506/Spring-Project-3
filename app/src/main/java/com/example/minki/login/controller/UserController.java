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
    public void signUp(@RequestBody UserSignUpVo userSignUpVo) throws Exception{
        userService.signUp(userSignUpVo);
    }

    //로그인
    @PostMapping("/signIn")
    public Long signIn(@RequestBody UserSignInVo userSignInVo){
        Long result;
        result = userService.signIn(userSignInVo);
        return result;
    }

}
