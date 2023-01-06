package com.example.minki.login.model.vo;

import com.example.minki.login.model.entity.UserEntity;
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

//    /* Dto -> Entity */
//    public UserEntity toEntity() {
//        UserEntity userEntity = UserEntity.builder()
//                .name(name)
//                .email(email)
//                .password(password)
//                .build();
//
//        return userEntity;
//    }
//
//
//    /* Entity -> Dto*/
//    public UserSignUpVo(UserEntity userEntity){
//        this.name = userEntity.getName();
//        this.email = userEntity.getEmail();
//        this.password = userEntity.getPassword();
//    }
}
