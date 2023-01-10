//package com.example.minki.photoFile.model.entity;
//
//import com.example.minki.BaseTimeEntity;
//import com.example.minki.store.model.entity.StoreEntity;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Entity(name = "Attachments")
//@Table(name = "ATTACHMENTS")
//public class Attachments extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ATTACHMENTS_ID")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "STORE_ID")
//    private StoreEntity storeEntity;
//
//    private String origFileName;
//
//    private String fileName;
//
//    private String filePath;
//
//    private Long fileSize;
//
//    @Builder
//    public Attachments(StoreEntity storeEntity, String origFileName, String fileName, String filePath, Long fileSize){
//        this.storeEntity = storeEntity;
//        this.origFileName = origFileName;
//        this.fileName = fileName;
//        this.filePath = filePath;
//        this.fileSize = fileSize;
//    }
//
//    public void setStore(StoreEntity storeEntity){
//        this.storeEntity = storeEntity;
//    }
//
//}
