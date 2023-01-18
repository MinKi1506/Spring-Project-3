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
    public String signUp(UserSignUpVo userSignUpVo) throws RuntimeException{
        String response;
        validateDuplicateEmail(userSignUpVo.getEmail()); //이메일 중복검사 -> 중복 시, DuplicateEmailException예외처리
//        userRepository.save(UserEntity.createUser(userVo));
        UserEntity result = userRepository.save(new UserEntity(userSignUpVo));
        if(null != result.getId() && !"".equals(result.getId())){
            response = "success";
        }else {
            response = "fail";
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
}
