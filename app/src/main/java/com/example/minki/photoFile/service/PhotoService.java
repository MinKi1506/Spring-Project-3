package com.example.minki.photoFile.service;

import com.example.minki.photoFile.model.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    FileVo saveFile(MultipartFile multipartFile) throws IOException;
}
