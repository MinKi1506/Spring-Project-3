package com.example.minkiApi.login.service;


import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;

public interface UserService {
    Long signUp(UserSignUpVo userSignUpVo);

    Long signIn(UserSignInVo userSignInVo);

    String getUserName(Long userId);
}
