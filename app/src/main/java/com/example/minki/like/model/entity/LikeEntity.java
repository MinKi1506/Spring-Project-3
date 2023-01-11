package com.example.minki.like.model.entity;

import com.example.minki.login.model.entity.UserEntity;
import com.example.minki.store.model.entity.StoreEntity;
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
public class LikeEntity {

    private Long id;

    private StoreEntity storeEntity;

    private UserEntity userEntity;

    private boolean status; //true = 좋아요, false = 좋아요 취소

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;  //날짜

    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public static LikeEntity toLikeEntity(StoreEntity storeEntity, UserEntity userEntity){
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserEntity(userEntity);
        likeEntity.setStoreEntity(storeEntity);
        return likeEntity;
    }


}
