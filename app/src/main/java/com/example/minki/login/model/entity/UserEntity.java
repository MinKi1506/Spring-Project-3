package com.example.minki.login.model.entity;

import com.example.minki.login.model.vo.UserSignInVo;
import com.example.minki.login.model.vo.UserSignUpVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{

    private Long id;
    private String name;
    private int age;
    private int gender;
    private String team;
    private String email;
    private String password;

    @Builder // builder = 클래스 자동생성 -> 생성자 대신 사용
    public UserEntity(String name, int age, int gender, String team, String email, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = team;
        this.email = email;
        this.password = password;
    }

    //유저 정보 수정 메소드
    public void update(String name, int age, int gender, String team, String email, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = team;
        this.email = email;
        this.password = password;
    }

    //VO를 Entity화 시키는 생성자
    public UserEntity(UserSignUpVo userSignUpVo) {
        this.name = userSignUpVo.getName();
        this.email = userSignUpVo.getEmail();
        this.password = userSignUpVo.getPassword();
    }

    public UserEntity(UserSignInVo userSignInVo) {
        this.email = userSignInVo.getEmail();
        this.password = userSignInVo.getPassword();
    }
}