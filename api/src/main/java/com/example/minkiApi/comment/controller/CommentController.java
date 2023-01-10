package com.example.minkiApi.comment.controller;

import com.example.minkiApi.comment.model.entity.Comment;
import com.example.minkiApi.comment.model.vo.CommentRequestVo;
import com.example.minkiApi.comment.service.CommentService;
import com.example.minkiApi.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/store/{storeId}/comment")
    public String createComment(@PathVariable Long storeId, @RequestBody CommentRequestVo commentRequestVo){
        commentService.createComment(storeId, commentRequestVo);
        return "comment is added";
    }

    //댓글 조회
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/store/{storeId}/comment")
    public List<Comment> getComment(@PathVariable Long storeId){
        List<Comment> commentList;
        commentList = commentService.getCommentList(storeId);
        return commentList;
    }

    //댓글 삭제
    @DeleteMapping("/store/{storeId}/comment/{commentId}")
    public List<Comment> deleteComment(@PathVariable Long storeId, @PathVariable Long commentId){
        List<Comment> commentList;
        commentList = commentService.deleteComment(commentId, storeId);
        return commentList;
    }

    //댓글 수정
    @PutMapping("/store/{storeId}/comment/{commentId}")
    public List<Comment> updateComment(@PathVariable Long storeId, @PathVariable Long commentId, @RequestBody CommentRequestVo commentRequestVo){
        List<Comment> commentList;
        commentList = commentService.updateComment(commentRequestVo, commentId, storeId);
        return  commentList;
    }




}
