package com.example.minkiApi.diet.repository;

import com.example.minkiApi.diet.model.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietRepository extends JpaRepository<DietEntity, Long> {

    //아이디 내림차순으로 전부 가져오기
    List<DietEntity> findAllByOrderByIdDesc();

    //맛집 포스팅 Id 최댓값 가져오기
    @Query(value = "select MAX(diet_id) from diet_entity", nativeQuery = true)
    Long getMaxDietId();

    //포스팅 제목으로 리스트 조회
    List<DietEntity> findByTitleContaining(String title);

    //가게 상호명으로 리스트 조회
    List<DietEntity> findByGoalContaining(String goal);

    //포스팅 본문 내용으로 리스트 조회
    List<DietEntity> findByContentContaining(String content);

    //최다 추천(Best) 맛집
    DietEntity findTop1ByOrderByCheerCountDesc();

    //유저가 업로드한 맛집 포스팅 갯수
    Long countByWriterId(Long userId);

}
