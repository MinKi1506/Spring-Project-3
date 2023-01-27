package com.example.minki.login.service.impl;

import com.example.minki.exception.DuplicateEmailException;
import com.example.minki.login.model.entity.UserEntity;
import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;
import com.example.minki.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    WebClient webClient;

    //회원가입
    @Override
    @Transactional
    public Long signUp(@RequestBody UserSignUpVo userSignUpVo) throws RuntimeException {
        Long response;
        response = webClient.post()
                .uri("/signUp")
                .bodyValue(userSignUpVo)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }

    //로그인
    @Override
    public Long signIn(@RequestBody UserSignInVo userSignInVo) {
        Long response;
        response = webClient.post()
                .uri("/signIn")
                .bodyValue(userSignInVo)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }

    //유저 이름 가져오기
    @Override
    public String getUserName(Long userId) {
        String response;
        response = webClient.get()
                .uri("/getUserName/"+userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
