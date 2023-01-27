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
//@CrossOrigin("*")
public class StoreFileController {

    @Autowired
    private StoreFileService storeFileService;

    private final static String imageDirectory = Paths.get("").toAbsolutePath()+"/images/";

    //(디렉토리가 없다면) 디렉토리 만들어 주기
    public StoreFileController(){
        File file = new File(imageDirectory);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    //파일을 vo에 담아서 지정한 디렉토리에 업로드하기
    @PostMapping(value = "/dto")
    @ResponseBody
    public String uploadFileInVo(@ModelAttribute MultipartVo multipartVo){
        MultipartFile multipartFile = multipartVo.getStoreFile();
        try (FileOutputStream writer = new FileOutputStream("./images/"+multipartFile.getOriginalFilename())){
            writer.write(multipartFile.getBytes());
            storeFileService.addStoreFile(multipartVo);    //db에 저장
        } catch (Exception e) {
            return "파일 업로드 실패";
        }
        return "파일 업로드 성공";
    }

   //파일의 확장자명 추출
    private String getExtension(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if(index > -1){
            return fileName.substring(index);
        }
        return "";
    }

    //파라미터로 요청된 fileName과 동일한 이름의 파일을 반환
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> image(@PathVariable String fileName) throws FileNotFoundException {
        String filePath = imageDirectory + fileName;
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(filePath));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

    //디렉토리 내 파일 전부 가져오기(=파일들의 이름을 리스트로 반환)
    @GetMapping("/files")
    public List<String> getFileNames(){
        return Stream.of(new File(imageDirectory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    //db에서 storeId로 필요한 StoreFile만 추출 -> 이걸 array로 정의하여 array내부의 값들(path)과 일치하는 사진만 뿌려준다
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


    //프론트(Vue)단에서 보낸 multipartFile/FormData 내부에 "files"라는 이름의 파일들이 서버로 전달된다.
    //파일을 저장할 경로를 결정한 뒤, 출력 스트림(ouput stream)을 통해 업로드된 파일의 바이트 정보를 출력
    //업로드한 파일들은 서버의 파일 시스템에 저장됨.
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

   //업로드된 파일 삭제
   @DeleteMapping("/deleteFile/{fileName}")
   public int deleteFile(@PathVariable String fileName) {
        int response;
//        String realFileName = fileName.substring(0, fileName.length() - 1); //받아온 fileName에 자꾸 =가 붙어서 이것을 제거해주는 역할
       String filePath = imageDirectory + fileName;
       File toDeleteFile = new File(filePath);

       try {
           if (toDeleteFile.exists()) {
               toDeleteFile.delete();
               storeFileService.deleteStoreFile(fileName); // db에서 삭제
               response = 1;
           } else {
               response = 0;
           }
       } catch (Exception e) {
           System.out.println("파일 삭제에러:::" + e);
           response = 0;
       }
       return response;
   }
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

