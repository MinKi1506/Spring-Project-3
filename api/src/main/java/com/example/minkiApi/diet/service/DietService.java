package com.example.minkiApi.diet.service;

import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.diet.model.vo.AddDietVo;

import java.util.List;
import java.util.Optional;

public interface DietService {
    //포스팅 추가
    String addDiet(AddDietVo addDietVo);

    //포스팅 수정
    Optional<DietEntity> updateDiet(Long id, AddDietVo addDietVo);

    //포스팅 리스트 조회
    List<DietEntity> getDietList();

    //포스팅 1개 조회
    Optional<DietEntity> getDiet(Long id);

    //포스팅 삭제
    String deleteDiet(Long id);

    //최대 storeId 조회
    Long getMaxDietId();

    //포스팅 제목으로 리스트 조회
    List<DietEntity> searchDietByTitle(String title);

    //다이어트 목표로 리스트 조회
    List<DietEntity> searchDietByGoal(String goal);

    //포스팅 본문 내용으로 리스트 조회
    List<DietEntity> searchDietByContent(String content);

    //최다 추천(Best) 다이어트
    DietEntity getBestDiet();

    //최다 추천(Best) 맛집
    Long getCountDietByUserId(Long userId);
}
