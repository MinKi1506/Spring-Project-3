package com.example.minki.comment.controller;

import com.example.minki.comment.model.entity.Comment;
import com.example.minki.comment.model.vo.CommentRequestVo;
import com.example.minki.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 쓰기
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/store/{storeId}/comment")
    public String createComment(@PathVariable Long storeId, @RequestBody CommentRequestVo commentRequestVo){
        commentService.createComment(storeId, commentRequestVo);
        return "comment is added";
    }

    // 댓글 조회
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/store/{storeId}/comment")
    public List<Comment> getComment(@PathVariable Long storeId){
        List<Comment> commentList;
        commentList = commentService.getCommentList(storeId);
        return commentList;
    }

    //댓글 삭제
    @DeleteMapping("/store/{storeId}/comment/{commentId}")
    public void deleteComment(@PathVariable Long storeId, @PathVariable Long commentId){
        commentService.deleteComment(commentId, storeId);
    }

    //댓글 수정
    @PutMapping("/store/{storeId}/comment/{commentId}")
    public List<Comment> updateComment(@PathVariable Long storeId, @PathVariable Long commentId, @RequestBody CommentRequestVo commentRequestVo){
        List<Comment> commentList;
        commentList = commentService.updateComment(commentRequestVo, commentId, storeId);
        return commentList;
    }

//    //댓글 갯수 조회
//    @GetMapping("/store/{storeId}/comment/count")
//    public Long commentCount(@PathVariable Long storeId){
//        Long response;
//        response = commentService.getCommentCount(storeId);
//        return response;
//    }



}
