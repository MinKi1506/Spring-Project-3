package com.example.minki.comment.service.impl;


import com.example.minki.comment.model.entity.Comment;
import com.example.minki.comment.model.vo.CommentRequestVo;
import com.example.minki.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    WebClient webClient;

    // 댓글 등록
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String createComment(@PathVariable Long storeId, @RequestBody CommentRequestVo commentRequestVo) {
        String response;
        response = webClient.post()
                .uri("/store/"+storeId+"/comment")
                .bodyValue(commentRequestVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    // 댓글 리스트 조회
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Comment> getCommentList(@PathVariable Long storeId) {
        List<Comment> commentList;
        commentList = webClient.get()
                .uri("/store/"+storeId+"/comment")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Comment.class)
                .toStream()
                .collect(Collectors.toList());
        return commentList;
    }


    // 댓글 삭제
    @Transactional
    @Override
    public void deleteComment(Long commentId, Long storeId) {
        List<Comment> commentList;
        commentList = webClient.delete()
                .uri("/store/"+storeId+"/comment/"+commentId)
                .retrieve()
                .bodyToFlux(Comment.class)
                .toStream()
                .collect(Collectors.toList());
    }

    //댓글 수정
    @Override
    public List<Comment> updateComment(CommentRequestVo commentRequestVo, Long commentId, Long storeId) {
        List<Comment> commentList;
        commentList = webClient.put()
                .uri("/store/"+storeId+"/comment/"+commentId)
                .bodyValue(commentRequestVo)
                .retrieve()
                .bodyToFlux(Comment.class)
                .toStream()
                .collect(Collectors.toList());
        return commentList;
    }

//    //댓글 갯수 조회
//    @Override
//    public Long getCommentCount(Long storeId) {
//        Long response;
//        response = webClient.get()
//                .uri("/store/"+storeId+"/comment/count")
//                .retrieve()
//                .bodyToMono(Long.class)
//                .block();
//        return response;
//    }
//
//    //댓글 수정
//    @Transactional
//    @Override
//    public List<Comment> editComment(Comment comment, long commentNo, long postNo) {
//        Optional<Comment> newComment =this.commentRepository.findById(commentNo);
//        newComment.ifPresent(origin->{
//            origin.setContent(comment.getContent());
//            this.commentRepository.save(origin);
//        });
//        return this.commentRepository.getCommentsOfPost(postNo);
//    }

}
