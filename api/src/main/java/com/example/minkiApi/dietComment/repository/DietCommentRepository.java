package com.example.minkiApi.dietComment.repository;

import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.dietComment.model.entity.DietComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietCommentRepository extends JpaRepository<DietComment, Long> {

    //앞단에서 받은 dietId로 dietEntity를 검색하여 해당 포스팅의 댓글 전체 조회하기
    List<DietComment> findAllByPost(DietEntity dietEntity);

    //앞단에서 받은 dietId로 dietEntity를 검색하여 해당 포스팅의 댓글 갯수 조회하기
    Long countByPost(DietEntity dietEntity);

}
