package com.example.minki.dietComment.service;

import com.example.minki.dietComment.model.entity.DietComment;
import com.example.minki.dietComment.model.vo.DietCommentRequestVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DietCommentService {

    // 댓글 등록
    @Transactional
    String createDietComment(Long dietId, DietCommentRequestVo dietCommentRequestVo);

    //댓글 리스트
    @Transactional(readOnly = true)
    List<DietComment> getDietCommentList(Long dietId);

    // 댓글 삭제
    @Transactional
    List<DietComment> deleteDietComment(Long dietCommentId, Long dietId);

    //댓글 수정
    @Transactional
    List<DietComment> updateDietComment(DietCommentRequestVo dietCommentRequestVo, Long dietCommentId, Long dietId);
}
