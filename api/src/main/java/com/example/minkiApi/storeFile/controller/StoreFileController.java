package com.example.minkiApi.storeFile.controller;

import com.example.minkiApi.storeFile.model.Vo.MultipartVo;
import com.example.minkiApi.storeFile.model.entity.StoreFileEntity;
import com.example.minkiApi.storeFile.service.StoreFileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@RestController
@CrossOrigin("*")
public class StoreFileController {

    @Autowired
    private StoreFileService storeFileService;

    private final static String imageDirectory = Paths.get("").toAbsolutePath()+"/images/";

    public StoreFileController(){
        File file = new File(imageDirectory);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    //vo에 담아서 사진 업로드하기
    @PostMapping(value = "/dto")
    @ResponseBody
    public String uploadFileInVo(@ModelAttribute MultipartVo multipartVo){
        MultipartFile multipartFile = multipartVo.getStoreFile();
        try (FileOutputStream writer = new FileOutputStream("./images/"+multipartFile.getOriginalFilename())){
            writer.write(multipartFile.getBytes());
            storeFileService.addStoreFile(multipartVo);
        } catch (Exception e) {
            return "파일 업로드 실패";
        }
        return "파일 업로드 성공";
    }

    private String getExtension(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if(index > -1){
            return fileName.substring(index);
        }
        return "";
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> image(@PathVariable String fileName) throws FileNotFoundException {
        String filePath = imageDirectory + fileName;
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(filePath));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

    @GetMapping("/files")
    public List<String> getFileNames(){
        return Stream.of(new File(imageDirectory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

//    //db에서 storeId로 뽑아온다 -> 이걸 array로 정의하여 array내부의 값들(path)과 일치하는 사진만 뿌려준다
//    @GetMapping("/files/{storeId}")
//    public List<String> getFileNames(@PathVariable Long storeId){
//        List<StoreFileEntity> filteredFileList = storeFileService.getStoreFileListByStoreId(storeId);
//
//        return Stream.of(new File(imageDirectory).listFiles())
//                .filter(list -> list.equals(filteredFileList))
//                .map(File::getName)
//                .collect(Collectors.toList());
//    }

    //db에서 storeId로 뽑아온다 -> 이걸 array로 정의하여 array내부의 값들(path)과 일치하는 사진만 뿌려준다
    @GetMapping("/files/{storeId}")
    public List<String> getFileNames(@PathVariable Long storeId){
        List<StoreFileEntity> filteredFileList = storeFileService.getStoreFileListByStoreId(storeId);
        List<String> newList = new ArrayList<>();

        for (int i = 0; i < filteredFileList.size(); i++) {
            String storeFileName = filteredFileList.get(i).getStoreFile().toString();
            newList.add(storeFileName);
            System.out.println(":::::"+storeFileName);
        }
        return newList;
//        return Stream.of(new File(imageDirectory).listFiles())
//                .filter(old -> filteredFileList.anyMatch(Predicate.isEqual()))
//                .map(File::getName)
//                .collect(Collectors.toList());
    }

    @PostMapping(value = "/files")
    public void uploadFiles(@RequestParam("files") List<MultipartFile> files){
        for (MultipartFile multipartFile : files){
            String filePath = imageDirectory + UUID.randomUUID()+getExtension(multipartFile);
            try(FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e){
                log.error(e.getMessage(), e);
                throw new RuntimeException("파일 업로드에 실패했습니다.");
            }
        }
    }





}
