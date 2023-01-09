package com.example.minki.store.model.vo;

import com.example.minki.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddStoreVo {
    private String title;
    private LocalDateTime editDate;
    private String content;
    private String storeName;
    private String address;
    private String menu;
    private int travelTime;

    //Entity를 Vo화 시키는 생성자
    public AddStoreVo(StoreEntity storeEntity){
        this.title = storeEntity.getTitle();
        this.editDate = storeEntity.getEditDate();
        this.content = storeEntity.getContent();
        this.storeName = storeEntity.getStoreName();
        this.address = storeEntity.getAddress();
        this.menu = storeEntity.getMenu();
        this.travelTime = storeEntity.getTravelTime();
    }
}
