package com.example.minkiApi.dietFile.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DietMultipartVo {

    private MultipartFile dietFile;

    private Long dietId;
}
