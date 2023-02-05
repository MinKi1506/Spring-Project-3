package com.example.minkiApi.cheer.model.entity;

import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.login.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class CheerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id", nullable = false)
    private DietEntity dietEntity;

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

    public static CheerEntity toCheerEntity(DietEntity dietEntity, UserEntity userEntity){
        CheerEntity cheerEntity = new CheerEntity();
        cheerEntity.setUserEntity(userEntity);
        cheerEntity.setDietEntity(dietEntity);
        return cheerEntity;
    }
}
