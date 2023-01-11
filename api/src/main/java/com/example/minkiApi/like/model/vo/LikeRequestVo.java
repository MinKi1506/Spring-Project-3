package com.example.minkiApi.like.model.vo;

import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestVo {

    private Long userId;
    private Long storeId;
}
