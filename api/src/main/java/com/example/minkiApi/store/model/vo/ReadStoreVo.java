package com.example.minkiApi.store.model.vo;

import com.example.minkiApi.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadStoreVo {

    private String title;
    private LocalDateTime createdDateTime;
    private String content;
    private int travelTime;

    private Long likeCount;

    //Entity를 Vo화 시키는 생성자
    public ReadStoreVo(StoreEntity storeEntity){
        this.title = storeEntity.getTitle();
        this.createdDateTime = storeEntity.getCreatedDateTime();
        this.content = storeEntity.getContent();
        this.travelTime = storeEntity.getTravelTime();
    }
}
