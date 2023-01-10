package com.example.minki.photoFile.service.impl;

import com.example.minki.photoFile.model.vo.FileVo;
import com.example.minki.photoFile.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {


    @Override
    public FileVo saveFile(MultipartFile multipartFile) throws IOException {
        //예외 체크
        if(!StringUtils.hasText(multipartFile.getOriginalFilename())){
            return null;
        }

        //성공 로직
        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);

        String fullPath = findFinalPath(storeFileName);
        multipartFile.transferTo(new File(fullPath));

        return new FileVo(storeFileName, originalFileName);
    }


    public String findFinalPath(String storeFileName){
//        return fileDirectory(=파일 경로 얘기하는듯) + storeFileName;
        return "프론트 구현후 다시 구성하자";
    }

    public String createStoreFileName(String originalFileName){
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);
        return uuid + "." + ext;
    }

    public String extractExt(String originalFileName){
        int position = originalFileName.lastIndexOf(".");
        return originalFileName.substring(position + 1);
    }
}
