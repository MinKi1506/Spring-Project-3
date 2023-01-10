package com.example.minkiApi.store.controller;


import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;
import com.example.minkiApi.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<StoreEntity> storeEntity = this.storeService.updateStore(id, addStoreVo);
        return "updated";
    }

    //게시글 목록 불러오기
    @GetMapping("/getStoreList")
    public List<StoreEntity> getStoreList(){
        List<StoreEntity> storeList = new ArrayList<>();
        storeList = storeService.getStoreList();
        return storeList;
    }


    //게시글 하나 불러오기
    @GetMapping("/getStore/{id}")
    public Optional<StoreEntity> getStore(@PathVariable Long id){
        Optional<StoreEntity> storeEntity = storeService.getStore(id);
        return storeEntity;
    }


    //게시글 삭제
    @DeleteMapping("/deleteStore/{id}")
    public String deleteStore(@PathVariable Long id){
        storeService.deleteStore(id);
        return "deleted";
    }
}
