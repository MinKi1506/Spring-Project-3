package com.example.minki.store.service.impl;

import com.example.minki.store.model.entity.StoreEntity;
import com.example.minki.store.model.vo.AddStoreVo;
import com.example.minki.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Autowired
    WebClient webClient;
    //포스팅 작성
    @Override
    @Transactional
//    public String addStore(@RequestBody AddStoreVo addStoreVo) {
    public String addStore(AddStoreVo addStoreVo) {
        String response;
        response = webClient.post()
                .uri("/addStore")
                .bodyValue(addStoreVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    //포스팅 수정
    @Transactional
    @Override
    public String updateStore(Long id, AddStoreVo addStoreVo) {
        String response;
        response = webClient.put()
                .uri("/updateStore/"+id)
                .bodyValue(addStoreVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    //포스팅 삭제
    @Override
    @Transactional
    public String deleteStore(Long id) {
        webClient.delete()
                .uri("/deleteStore/"+id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "delete success";
    }

    //게시글 조회
    @Override
    public StoreEntity getStore(Long id) {
        StoreEntity storeEntity;
        storeEntity = webClient.get()
                .uri("/getStore/"+id)
                .retrieve()
                .bodyToMono(StoreEntity.class)
                .block();
        return storeEntity;
    }

    //게시글 리스트 조회
    @Override
    public List<StoreEntity> getStoreList() {
        List<StoreEntity> storeEntityList;
        storeEntityList = webClient.get()
                .uri("/getStoreList")
                .retrieve()
                .bodyToFlux(StoreEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return storeEntityList;
    }

    //최대 storeId 조회
    @Override
    public Long getMaxStoreId() {
        Long response;
        response = webClient.get()
                .uri("/getMaxStoreId")
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }

    //제목으로 리스트 검색
    @Override
    public List<StoreEntity> searchStoreByTitle(String title) {
        List<StoreEntity> storeEntityList;
        storeEntityList = webClient.get()
                .uri("/searchStoreByTitle/"+title)
                .retrieve()
                .bodyToFlux(StoreEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return storeEntityList;
    }

    //가게명으로 리스트 검색
    @Override
    public List<StoreEntity> searchStoreByStoreName(String storeName) {
        List<StoreEntity> storeEntityList;
        storeEntityList = webClient.get()
                .uri("/searchStoreByStoreName/"+storeName)
                .retrieve()
                .bodyToFlux(StoreEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return storeEntityList;
    }

    //본문 내용으로 리스트 검색
    @Override
    public List<StoreEntity> searchStoreByContent(String content) {
        List<StoreEntity> storeEntityList;
        storeEntityList = webClient.get()
                .uri("/searchStoreByContent/"+content)
                .retrieve()
                .bodyToFlux(StoreEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return storeEntityList;
    }


}
