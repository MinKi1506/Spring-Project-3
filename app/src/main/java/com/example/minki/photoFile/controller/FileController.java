package com.example.minki.photoFile.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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

    //multiPartFiledml 다운로드 (웹 이미지 렌더링)
//    @ResponseBody
//    @GetMapping("/images/{storeFileName}")
//    public Resource imageDownLoad(@PathVariable String storeFileName) throws MalformedURLException{
//        return UrlResource("file:"+filestore(=파일의 절대경로 말하는듯!).findFinalPath(storeFileName));
//    }

}
