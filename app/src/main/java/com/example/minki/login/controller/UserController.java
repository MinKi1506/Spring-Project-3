package com.example.minki.login.controller;

import com.example.minki.login.model.vo.UserVo;
import com.example.minki.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/signUp")
    public void signUpUser(@RequestBody @Valid UserVo userVo) throws Exception{
        userService.signUpUser(userVo);
    }

}
