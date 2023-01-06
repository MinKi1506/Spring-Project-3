package com.example.minki.login.service;

import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;

public interface UserService {
    void signUp(UserSignUpVo userSignUpVo);

    Long signIn(String email, String password);
}
