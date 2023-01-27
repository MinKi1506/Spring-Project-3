package com.example.minkiApi.login.model.entity;


import com.example.minkiApi.like.model.entity.LikeEntity;
import com.example.minkiApi.login.model.vo.UserSignInVo;
import com.example.minkiApi.login.model.vo.UserSignUpVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private int age;
    private int gender;
    private String team;
    private String email;
    private String password;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<LikeEntity> likeEntityList;

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