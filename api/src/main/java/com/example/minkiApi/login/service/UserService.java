package com.example.minkiApi.login.service;


import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;

public interface UserService {
    String signUp(UserSignUpVo userSignUpVo);

    Long signIn(UserSignInVo userSignInVo);
}
