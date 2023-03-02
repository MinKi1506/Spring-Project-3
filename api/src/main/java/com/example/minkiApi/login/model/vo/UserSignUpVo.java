package com.example.minkiApi.login.model.vo;

import com.example.minkiApi.login.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpVo {

//    private Long id;
    private String name;
    private String email;
    private String password;

//    //Entity를 VO화 시키는 생성자
    public UserSignUpVo(UserEntity userEntity){
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
    }
}
