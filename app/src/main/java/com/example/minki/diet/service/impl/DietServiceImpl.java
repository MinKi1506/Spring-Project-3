package com.example.minki.diet.service.impl;


import com.example.minki.diet.model.entity.DietEntity;
import com.example.minki.diet.model.vo.AddDietVo;
import com.example.minki.diet.service.DietService;
import com.example.minki.store.model.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DietServiceImpl implements DietService {

    @Autowired
    WebClient webClient;

    //게시글 작성
    @Override
    @Transactional
//    public String addDiet(@RequestBody AddDietVo addDietVo) {
    public String addDiet(AddDietVo addDietVo) {
        String response;
        response = webClient.post()
                .uri("/addDiet")
                .bodyValue(addDietVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }


    //게시글 수정
    @Transactional
    @Override
    public String updateDiet(Long id, AddDietVo addDietVo) {
        String response;
        response = webClient.put()
                .uri("/updateDiet/"+id)
                .bodyValue(addDietVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    //게시글 삭제
    @Override
    @Transactional
    public String deleteDiet(Long id) {
        webClient.delete()
                .uri("/deleteDiet/"+id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "delete success";
    }

    //게시글 조회
    @Override
    public DietEntity getDiet(Long id) {
        DietEntity dietEntity;
        dietEntity = webClient.get()
                .uri("/getDiet/"+id)
                .retrieve()
                .bodyToMono(DietEntity.class)
                .block();
        return dietEntity;
    }

    //게시글 리스트 조회
    @Override
    public List<DietEntity> getDietList() {
        List<DietEntity> dietEntityList;
        dietEntityList = webClient.get()
                .uri("/getDietList")
                .retrieve()
                .bodyToFlux(DietEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return dietEntityList;
    }
//
//    //최대 dietId 조회
//    @Override
//    public Long getMaxDietId() {
//        Long response = this.dietRepository.getMaxDietId();
//        return response;
//    }

    //포스팅 제목으로 리스트 조회
    @Override
    public List<DietEntity> searchDietByTitle(String title) {
        List<DietEntity> dietEntityList;
        dietEntityList = webClient.get()
                .uri("/searchDietByTitle/"+title)
                .retrieve()
                .bodyToFlux(DietEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return dietEntityList;
    }

    //목표로 리스트 조회
    @Override
    public List<DietEntity> searchDietByGoal(String goal) {
        List<DietEntity> dietEntityList;
        dietEntityList = webClient.get()
                .uri("/searchDietByGoal/"+goal)
                .retrieve()
                .bodyToFlux(DietEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return dietEntityList;
    }

    //포스팅 본문 내용으로 리스트 조회
    @Override
    public List<DietEntity> searchDietByContent(String content) {
        List<DietEntity> dietEntityList;
        dietEntityList = webClient.get()
                .uri("/searchDietByContent/"+content)
                .retrieve()
                .bodyToFlux(DietEntity.class)
                .toStream()
                .collect(Collectors.toList());
        return dietEntityList;
    }

    //최다 추천(Best) 다이어트
    @Override
    public DietEntity getBestDiet() {
        DietEntity response;
        response = webClient.get()
                .uri("/getBestDiet")
                .retrieve()
                .bodyToMono(DietEntity.class)
                .block();
        return response;
    }

    //내가올린 다이어트 포스팅 갯수
    @Override
    public Long getCountDietByUserId(Long userId) {
        Long response;
        response = webClient.get()
                .uri("/getCountDiet/"+userId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        return response;
    }
    
    
}
