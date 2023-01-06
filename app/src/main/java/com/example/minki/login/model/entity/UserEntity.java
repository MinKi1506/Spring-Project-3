package com.example.minki.login.model.entity;

import com.example.minki.login.model.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ColumnDefault("default value")
    private String name;

    @ColumnDefault("default value")
    private int age;

    @ColumnDefault("default value")
    private int gender;
    @ColumnDefault("default value")
    private String team;
    private String email;
    private String password;

    @Builder // builder = 클래스 자동생성 -> 생성자 대신 사용
    public UserEntity(String name, int age, int gender, String team, String email, String password){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = team;
        this.email = email;
        this.password = password;
    }

    //유저 정보 수정 메소드
    public void update(String name, int age, int gender, String team, String email, String password){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.team = team;
        this.email = email;
        this.password = password;
    }

    public static UserEntity createUser(UserVo userVo){
        return new UserEntity(userVo);
    }



    //VO를 Entity화 시키는 메소드
    public UserEntity(UserVo userVo){
        this.name = userVo.getName();
        this.email = userVo.getEmail();
        this.password = userVo.getPassword();
    }

}
