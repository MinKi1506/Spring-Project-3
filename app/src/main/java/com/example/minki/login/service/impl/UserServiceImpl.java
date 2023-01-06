package com.example.minki.login.service.impl;

import com.example.minki.exception.DuplicateEmailException;
import com.example.minki.exception.ValidateSignInException;
import com.example.minki.login.model.entity.UserEntity;
import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;
import com.example.minki.login.repository.UserRepository;
import com.example.minki.login.service.UserService;
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
    public void signUp(UserSignUpVo userSignUpVo) throws RuntimeException{
        validateDuplicateEmail(userSignUpVo.getEmail()); //이메일 중복검사 -> 중복 시, DuplicateEmailException예외처리
//        userRepository.save(UserEntity.createUser(userVo));
        userRepository.save(new UserEntity(userSignUpVo));

    }
    //이메일 중복검사 메서드
    private void validateDuplicateEmail(String email) throws RuntimeException{
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException();
        }
    }
    @Override
    public Long signIn(String email, String password) {
        Long result;
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(userEntity.get().getPassword().equals(password)){
            result = userEntity.get().getId();
        }else {
            result = 0L;
        }
        return result;
    }
}
