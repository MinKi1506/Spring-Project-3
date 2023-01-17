package com.example.minki.photoFile.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileEntity {

    private Long id;

    private String storeFileName;
    private String uploadFileName;


}
