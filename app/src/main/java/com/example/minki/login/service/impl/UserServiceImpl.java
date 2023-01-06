package com.example.minki.login.service.impl;

import com.example.minki.exception.DuplicateEmailException;
import com.example.minki.login.model.entity.UserEntity;
import com.example.minki.login.model.vo.UserVo;
import com.example.minki.login.repository.UserRepository;
import com.example.minki.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public void signUpUser(UserVo userVo) throws RuntimeException{
        validateDuplicateEmail(userVo.getEmail());
        userRepository.save(UserEntity.createUser(userVo));
    }

    private void validateDuplicateEmail(String email) throws RuntimeException{
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException();
        }
    }
}
