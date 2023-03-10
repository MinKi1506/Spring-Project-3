package com.example.minkiApi.comment.service.impl;

import com.example.minkiApi.comment.model.entity.Comment;
import com.example.minkiApi.comment.model.vo.CommentRequestVo;
import com.example.minkiApi.comment.repository.CommentRepository;
import com.example.minkiApi.comment.service.CommentService;
import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final StoreRepository storeRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Comment createComment(@PathVariable Long storeId, CommentRequestVo commentRequestVo) {
        Optional<StoreEntity> post = this.storeRepository.findById(storeId);
        commentRequestVo.setPost(post.get());
        Comment comment = new Comment();
        comment.setWriterId(commentRequestVo.getWriterId());
        comment.setContent(commentRequestVo.getContent());
        comment.setPost(commentRequestVo.getPost());
        commentRepository.save(comment);
        commentCountPlus(storeId);
        return comment;
    }

    //댓글 리스트 조회
    @Override
    @Transactional
    public List<Comment> getCommentList(@PathVariable Long storeId) {
        StoreEntity storeEntity = storeRepository.findById(storeId).get();
        return commentRepository.findAllByPost(storeEntity);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public List<Comment> deleteComment(@PathVariable Long commentId, Long storeId) {
        StoreEntity storeEntity = storeRepository.findById(storeId).get();
        this.commentRepository.deleteById(commentId); //해당 댓글 삭제 후
        commentCountMinus(storeId);
        return this.commentRepository.findAllByPost(storeEntity); // 삭제된 댓글을 제외한 댓글 새로고침
    }

    // 댓글 수정
    @Override
    public List<Comment> updateComment(CommentRequestVo commentRequestVo, Long commentId, Long storeId) {
        StoreEntity storeEntity = storeRepository.findById(storeId).get();
        Optional<Comment> comment = this.commentRepository.findById(commentId);
        comment.ifPresent(origin -> {
            origin.setContent(commentRequestVo.getContent());
            this.commentRepository.save(origin);
        });

        return this.commentRepository.findAllByPost(storeEntity); // 수정된 댓글을 반영한 댓글 새로고침
    }

//
//    //댓글 갯수 조회
//    @Override
//    public Long getCommentCount(Long storeId) {
//        Long response;
//        StoreEntity storeEntity;
//        storeEntity = storeRepository.findById(storeId).get();
//        response = this.commentRepository.countByPost(storeEntity);
//        return response;
//    }

    //맛집 포스팅 댓글 수 증가
    @Transactional
    public void commentCountPlus(Long storeId){
        Long response = storeRepository.findById(storeId).get().getCommentCount();
        response++;
        StoreEntity storeEntity;
        storeEntity = storeRepository.findById(storeId).get();
        storeEntity.setCommentCount(response);
        storeRepository.save(storeEntity);
    }


    //맛집 포스팅 댓글 수 감소
    @Transactional
    public void commentCountMinus(Long storeId){
        Long response = storeRepository.findById(storeId).get().getCommentCount();
        response--;
        StoreEntity storeEntity;
        storeEntity = storeRepository.findById(storeId).get();
        storeEntity.setCommentCount(response);
        storeRepository.save(storeEntity);
    }



}
