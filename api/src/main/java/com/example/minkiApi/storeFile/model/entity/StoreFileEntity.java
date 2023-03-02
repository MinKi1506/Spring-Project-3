package com.example.minkiApi.storeFile.model.entity;

import com.example.minkiApi.storeFile.model.Vo.MultipartVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String storeFile;

    private Long storeId;

    public StoreFileEntity(MultipartVo multipartVo){
        this.storeFile = multipartVo.getStoreFile().getOriginalFilename();
        this.storeId = multipartVo.getStoreId();
    }

}
