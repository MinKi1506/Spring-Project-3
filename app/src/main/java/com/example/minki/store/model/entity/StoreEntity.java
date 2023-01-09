package com.example.minki.store.model.entity;

import com.example.minki.store.model.vo.AddStoreVo;
import com.example.minki.store.model.vo.ReadStoreVo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime editDate;
    private String content;
    private String storeName;
    private String address;
    private String menu;
    private int travelTime;

    @Builder // builder = 클래스 자동생성 -> 생성자 대신 사용
    public StoreEntity(String title, LocalDateTime editDate, String content, String storeName, String address, String menu, int travelTime){
        this.title = title;
        this.editDate = editDate;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
    }

    //맛집 포스팅 정보 수정 메소드
    public void update(String title, LocalDateTime editDate, String content, String storeName, String address, String menu, int travelTime){
        this.title = title;
        this.editDate = editDate;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
    }
    //VO를 Entity화 시키는 생성자
    public StoreEntity(AddStoreVo addStoreVo){
        this.title = addStoreVo.getTitle();
        this.editDate = addStoreVo.getEditDate();
        this.content = addStoreVo.getContent();
        this.storeName = addStoreVo.getStoreName();
        this.address = addStoreVo.getAddress();
        this.menu = addStoreVo.getMenu();
        this.travelTime = addStoreVo.getTravelTime();
    }

    //VO를 Entity화 시키는 생성자
    public StoreEntity(ReadStoreVo readStoreVo){
        this.title = readStoreVo.getTitle();
        this.editDate = readStoreVo.getEditDate();
        this.content = readStoreVo.getContent();
        this.travelTime = readStoreVo.getTravelTime();
    }

}
