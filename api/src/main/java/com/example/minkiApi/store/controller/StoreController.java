package com.example.minkiApi.store.controller;


import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;
import com.example.minkiApi.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin
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
        List<StoreEntity> storeList;
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


    //최대 storeId 조회
    @GetMapping("/getMaxStoreId")
    public Long getMaxStoreId(){
        Long response = storeService.getMaxStoreId();
        return response;
    }

    //제목으로 리스트 검색
    @GetMapping("/searchStoreByTitle/{title}")
    public List<StoreEntity> searchStoreByTitle(@PathVariable String title) {
        List<StoreEntity> storeList;
        storeList = storeService.searchStoreByTitle(title);
        return storeList;
    }

    //가게명으로 리스트 검색
    @GetMapping("/searchStoreByStoreName/{storeName}")
    public List<StoreEntity> searchStoreByStoreName(@PathVariable String storeName) {
        List<StoreEntity> storeList;
        storeList = storeService.searchStoreByStoreName(storeName);
        return storeList;
    }

    //본문 내용으로 리스트 검색
    @GetMapping("/searchStoreByContent/{content}")
    public List<StoreEntity> searchStoreByContent(@PathVariable String content) {
        List<StoreEntity> storeList;
        storeList = storeService.searchStoreByContent(content);
        return storeList;
    }


    //최다 추천(Best) 맛집
    @GetMapping("/getBestStore")
    public StoreEntity getBestStore(){
        StoreEntity response;
        response = storeService.getBestStore();
        return  response;
    }

    //내가올린 맛집 포스팅 갯수
    @GetMapping("/getCountStore/{userId}")
    public Long getCountStoreByUserId(@PathVariable Long userId){
        Long response;
        response = storeService.getCountStoreByUserId(userId);
        return  response;
    }

    //내가올린 다이어트 포스팅 갯수
}
