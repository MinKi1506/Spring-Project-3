package com.example.minkiApi.dietComment.service.impl;

import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.diet.repository.DietRepository;
import com.example.minkiApi.dietComment.model.entity.DietComment;
import com.example.minkiApi.dietComment.model.vo.DietCommentRequestVo;
import com.example.minkiApi.dietComment.repository.DietCommentRepository;
import com.example.minkiApi.dietComment.service.DietCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DietCommentServiceImpl implements DietCommentService {

    private final DietRepository dietRepository;
    private final DietCommentRepository dietCommentRepository;

    // 댓글 등록
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public DietComment createDietComment(@PathVariable Long dietId, DietCommentRequestVo dietCommentRequestVo) {
        Optional<DietEntity> post = this.dietRepository.findById(dietId);
        dietCommentRequestVo.setPost(post.get());
        DietComment dietComment = new DietComment();
        dietComment.setWriterId(dietCommentRequestVo.getWriterId());
        dietComment.setContent(dietCommentRequestVo.getContent());
        dietComment.setPost(dietCommentRequestVo.getPost());
        dietCommentRepository.save(dietComment);
        dietCommentCountPlus(dietId);
        return dietComment;
    }

    //댓글 리스트 조회
    @Override
    @Transactional
    public List<DietComment> getDietCommentList(@PathVariable Long dietId) {
        DietEntity dietEntity = dietRepository.findById(dietId).get();
        return dietCommentRepository.findAllByPost(dietEntity);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public List<DietComment> deleteDietComment(@PathVariable Long DietCommentId, Long dietId) {
        DietEntity dietEntity = dietRepository.findById(dietId).get();
        this.dietCommentRepository.deleteById(DietCommentId); //해당 댓글 삭제 후
        dietCommentCountMinus(dietId);
        return this.dietCommentRepository.findAllByPost(dietEntity); // 삭제된 댓글을 제외한 댓글 새로고침
    }

    // 댓글 수정
    @Override
    public List<DietComment> updateDietComment(DietCommentRequestVo dietCommentRequestVo, Long dietCommentId, Long dietId) {
        DietEntity dietEntity = dietRepository.findById(dietId).get();
        Optional<DietComment> dietComment = this.dietCommentRepository.findById(dietCommentId);
        dietComment.ifPresent(origin -> {
            origin.setContent(dietCommentRequestVo.getContent());
            this.dietCommentRepository.save(origin);
        });

        return this.dietCommentRepository.findAllByPost(dietEntity); // 수정된 댓글을 반영한 댓글 새로고침
    }


    //맛집 포스팅 댓글 수 증가
    @Transactional
    public void dietCommentCountPlus(Long dietId){
        Long response = dietRepository.findById(dietId).get().getDietCommentCount();
        response++;
        DietEntity dietEntity;
        dietEntity = dietRepository.findById(dietId).get();
        dietEntity.setDietCommentCount(response);
        dietRepository.save(dietEntity);
    }



    //맛집 포스팅 댓글 수 감소
    @Transactional
    public void dietCommentCountMinus(Long dietId){
        Long response = dietRepository.findById(dietId).get().getDietCommentCount();
        response--;
        DietEntity dietEntity;
        dietEntity = dietRepository.findById(dietId).get();
        dietEntity.setDietCommentCount(response);
        dietRepository.save(dietEntity);
    }


}
