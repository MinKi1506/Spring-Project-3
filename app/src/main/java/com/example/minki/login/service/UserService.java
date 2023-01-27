package com.example.minki.login.service;

import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;

public interface UserService {
    Long signUp(UserSignUpVo userSignUpVo);

    Long signIn(UserSignInVo userSignInVo);

    String getUserName(Long userId);
}
