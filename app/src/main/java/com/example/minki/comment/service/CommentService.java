package com.example.minki.comment.service;

import com.example.minki.comment.model.entity.Comment;
import com.example.minki.comment.model.vo.CommentRequestVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {

    // 댓글 등록
    @Transactional
    String createComment(Long storeId, CommentRequestVo commentRequestVo);

    //댓글 리스트
    @Transactional(readOnly = true)
    List<Comment> getCommentList(Long storeId);

    // 댓글 삭제
    @Transactional
    List<Comment> deleteComment(Long commentId, Long storeId);

    //댓글 수정
    @Transactional
    List<Comment> updateComment(CommentRequestVo commentRequestVo, Long commentId, Long storeId);


}
