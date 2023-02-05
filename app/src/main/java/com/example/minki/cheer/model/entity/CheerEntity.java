package com.example.minki.cheer.model.entity;


import com.example.minki.diet.model.entity.DietEntity;
import com.example.minki.login.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheerEntity {


    private Long id;

    private DietEntity dietEntity;

    private UserEntity userEntity;

    private boolean status; //true = 좋아요, false = 좋아요 취소
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;  //날짜

    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public static CheerEntity toCheerEntity(DietEntity dietEntity, UserEntity userEntity){
        CheerEntity cheerEntity = new CheerEntity();
        cheerEntity.setUserEntity(userEntity);
        cheerEntity.setDietEntity(dietEntity);
        return cheerEntity;
    }
}
