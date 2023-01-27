package com.example.minkiApi.store.model.entity;


import com.example.minkiApi.comment.model.entity.Comment;
import com.example.minkiApi.like.model.entity.LikeEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;
import com.example.minkiApi.store.model.vo.ReadStoreVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StoreEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;
    private String title;
    @CreatedDate
    private LocalDateTime createdDateTime;
    private String content;
    private String storeName;
    private String address;
    private String menu;
    private int travelTime;
    private Long likeCount = 0L;
    private Long commentCount = 0L;
    private Long writerId;

    @JsonIgnore
    @OneToMany(mappedBy = "post", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> commentList;

    @JsonIgnore
    @OneToMany(mappedBy = "storeEntity", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList;

//
//    @OneToMany(mappedBy = "store", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Attachments> attachments = new ArrayList<>();

    @Builder // builder = 클래스 자동생성 -> 생성자 대신 사용
    public StoreEntity(String title, LocalDateTime createdDateTime, String content, String storeName, String address, String menu, int travelTime, Long writerId){
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
        this.likeCount = 0L;
        this.commentCount = 0L;
        this.writerId = writerId;
    }

    //맛집 포스팅 정보 수정 메소드
    public void update(String title, LocalDateTime createdDateTime, String content, String storeName, String address, String menu, int travelTime,Long writerId){
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.content = content;
        this.storeName = storeName;
        this.address = address;
        this.menu = menu;
        this.travelTime = travelTime;
        this.writerId = writerId;
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
        this.writerId = addStoreVo.getWriterId();
    }

    //response VO를 Entity화 시키는 생성자
    public StoreEntity(ReadStoreVo readStoreVo){
        this.title = readStoreVo.getTitle();
        this.createdDateTime = readStoreVo.getCreatedDateTime();
        this.content = readStoreVo.getContent();
        this.travelTime = readStoreVo.getTravelTime();

    }

}
