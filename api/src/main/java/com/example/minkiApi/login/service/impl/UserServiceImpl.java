package com.example.minkiApi.login.service.impl;


import com.example.minkiApi.exception.DuplicateEmailException;
import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;
import com.example.minkiApi.login.repository.UserRepository;
import com.example.minkiApi.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public Long signUp(UserSignUpVo userSignUpVo) throws RuntimeException{
        Long response;
        validateDuplicateEmail(userSignUpVo.getEmail()); //이메일 중복검사 -> 중복 시, DuplicateEmailException예외처리
        UserEntity result = userRepository.save(new UserEntity(userSignUpVo));
        if(null != result.getId() && !"".equals(result.getId())){
            response = result.getId();
        }else {
            response = 0L;
        }
        return response;

    }
    //이메일 중복검사 메서드
    private void validateDuplicateEmail(String email) throws RuntimeException{
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException();
        }
    }
    @Override
    public Long signIn(UserSignInVo userSignInVo) {
        Long result;
        try {
            Optional<UserEntity> userEntity = userRepository.findByEmail(userSignInVo.getEmail());
            if(userEntity.get().getPassword().equals(userSignInVo.getPassword())){
            result = userEntity.get().getId();
        }else {
            result = 0L;
        }
            return result;
        }catch (Exception e){
            System.out.println(e);
            return 0L;
        }
    }

    //유저 닉네임 조회
    @Override
    public String getUserName(Long userId) {
        String response;
        try{
            Optional<UserEntity> userEntity = userRepository.findById(userId);
            response = userEntity.get().getName();
        }catch (Exception c){
            return "사용자명을 찾을 수 없습니다.";
        }
        return response;
    }
}
