package com.example.minki.store.model.vo;

import com.example.minki.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadStoreVo {

    private String title;
    private LocalDateTime editDate;
    private String content;
    private int travelTime;

    //Entity를 Vo화 시키는 생성자
    public ReadStoreVo(StoreEntity storeEntity){
        this.title = storeEntity.getTitle();
        this.editDate = storeEntity.getEditDate();
        this.content = storeEntity.getContent();
        this.travelTime = storeEntity.getTravelTime();
    }
}
