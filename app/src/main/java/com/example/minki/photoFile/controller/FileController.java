package com.example.minki.photoFile.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @PostMapping("/upload")
    public String savefile(@RequestParam String itemName, @RequestParam MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException{
        if (!multipartFile.isEmpty()){
//            String fullPath = fileDir(=파일 디렉토리 얘기하는듯!) + multipartFile.getOriginalFilename();
//            multipartFile.transferTo(new File(fullPath));   //파일을 fullPath경로에 저장
        }
        return "upload-form";
    }
}
