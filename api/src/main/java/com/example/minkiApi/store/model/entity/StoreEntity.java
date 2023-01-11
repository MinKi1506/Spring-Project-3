package com.example.minkiApi.store.model.entity;


import com.example.minkiApi.store.model.vo.AddStoreVo;
import com.example.minkiApi.store.model.vo.ReadStoreVo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;
    private String title;
    private LocalDateTime createdDateTime;
    private String content;
    private String storeName;
    private String address;
    private String menu;
    private int travelTime;
    private Long fileId;

    private int liked;
//
//    @OneToMany(mappedBy = "store", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Attachments> attachments = new ArrayList<>();

    @Builder // builder = 클래스 자동생성 -> 생성자 대신 사용
    public StoreEntity(String title, LocalDateTime createdDateTime, String content, String storeName, String address, String menu, int travelTime, Long fileId){
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
        this.fileId = fileId;
    }

    //맛집 포스팅 정보 수정 메소드
    public void update(String title, LocalDateTime createdDateTime, String content, String storeName, String address, String menu, int travelTime, Long fileId){
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
        this.fileId = fileId;
    }

    //request VO를 Entity화 시키는 생성자
    public StoreEntity(AddStoreVo addStoreVo){
        this.title = addStoreVo.getTitle();
        this.createdDateTime = addStoreVo.getCreatedDateTime();
        this.content = addStoreVo.getContent();
        this.storeName = addStoreVo.getStoreName();
        this.address = addStoreVo.getAddress();
        this.menu = addStoreVo.getMenu();
        this.travelTime = addStoreVo.getTravelTime();
        this.fileId = addStoreVo.getFileId();
    }

    //response VO를 Entity화 시키는 생성자
    public StoreEntity(ReadStoreVo readStoreVo){
        this.title = readStoreVo.getTitle();
        this.createdDateTime = readStoreVo.getCreatedDateTime();
        this.content = readStoreVo.getContent();
        this.travelTime = readStoreVo.getTravelTime();
    }

}
