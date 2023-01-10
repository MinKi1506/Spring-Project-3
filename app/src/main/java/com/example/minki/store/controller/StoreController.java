package com.example.minki.store.controller;

import com.example.minki.store.model.entity.StoreEntity;
import com.example.minki.store.model.vo.AddStoreVo;
import com.example.minki.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    @Autowired
    private final StoreService storeService;

    //게시글 작성
    @PostMapping("/addStore")
    public String addStore(@RequestBody AddStoreVo addStoreVo){
        storeService.addStore(addStoreVo);
        return "added";
    }

    //게시글 수정
    @PutMapping("/updateStore/{id}")
    public String updateStore(@PathVariable Long id, @RequestBody AddStoreVo addStoreVo){
        storeService.updateStore(id, addStoreVo);
        return "updated";
    }

    //게시글 삭제
    @DeleteMapping("/deleteStore/{id}")
    public String deleteStore(@PathVariable Long id){
        storeService.deleteStore(id);
        return "deleted";
    }


    //게시글 하나 불러오기
    @GetMapping("/getStore/{id}")
    public StoreEntity getStore(@PathVariable Long id){
        StoreEntity storeEntity = storeService.getStore(id);
        return storeEntity;
    }

    //게시글 목록 불러오기
    @GetMapping("/getStoreList")
    public List<StoreEntity> getStoreList(){
        List<StoreEntity> storeList;
        storeList = storeService.getStoreList();
        return storeList;
    }


}
