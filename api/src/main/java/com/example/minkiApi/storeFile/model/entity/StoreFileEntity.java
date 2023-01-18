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


//    // 원본 파일이름 과 서버에 저장된 파일 경로 를 분리한 이유?
//    // 동일한 이름을 가진 파일이 업로드가 된다면 오류가 생긴다.
//    // 이를 해결하기 위함
//    private String original_file_name;
//    private String stored_file_path;
//    private long file_size;

    public StoreFileEntity(MultipartVo multipartVo){
        this.storeFile = multipartVo.getStoreFile().getOriginalFilename();
        this.storeId = multipartVo.getStoreId();
    }

}
