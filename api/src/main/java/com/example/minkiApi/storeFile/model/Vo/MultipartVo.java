package com.example.minkiApi.storeFile.model.Vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MultipartVo {

    private MultipartFile storeFile;

    private Long storeId;

}
