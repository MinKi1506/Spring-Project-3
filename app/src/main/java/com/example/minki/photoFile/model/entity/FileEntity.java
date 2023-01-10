package com.example.minki.photoFile.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class FileEntity {

    private Long id;

    private String storeFileName;
    private String uploadFileName;


}
