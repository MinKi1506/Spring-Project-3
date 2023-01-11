package com.example.minkiApi.like.model.entity;

import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity storeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    private boolean status; //true = 좋아요, false = 좋아요 취소

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;  //날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. = DB에 값을 넣으면 자동으로 실행
    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public static LikeEntity toLikeEntity(StoreEntity storeEntity, UserEntity userEntity){
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserEntity(userEntity);
        likeEntity.setStoreEntity(storeEntity);
        return likeEntity;
    }


//    //좋아요 하기
//    public LikeEntity(StoreEntity storeEntity, UserEntity userEntity){
//        this.storeEntity = storeEntity;
//        this.userEntity = userEntity;
//        this.status = true;
//    }
//
//    //좋아요 취소
//    public void unLikeBoard(StoreEntity storeEntity){
//        this.status = false;
//        storeEntity.setLiked(storeEntity.getLiked() - 1); //좋아요 총 갯수 감소
//    }


}
