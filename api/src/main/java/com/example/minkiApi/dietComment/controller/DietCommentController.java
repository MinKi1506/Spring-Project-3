package com.example.minkiApi.dietComment.controller;

import com.example.minkiApi.dietComment.model.entity.DietComment;
import com.example.minkiApi.dietComment.model.vo.DietCommentRequestVo;
import com.example.minkiApi.dietComment.service.DietCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DietCommentController {

    private final DietCommentService dietCommentService;

    // 댓글 생성
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/diet/{dietId}/dietComment")
    public String createDietComment(@PathVariable Long dietId, @RequestBody DietCommentRequestVo dietCommentRequestVo){
        dietCommentService.createDietComment(dietId, dietCommentRequestVo);
        return "DietComment is added";
    }

    //댓글 조회
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/diet/{dietId}/dietComment")
    public List<DietComment> getDietComment(@PathVariable Long dietId){
        List<DietComment> dietCommentList;
        dietCommentList = dietCommentService.getDietCommentList(dietId);
        return dietCommentList;
    }

    //댓글 삭제
    @DeleteMapping("/diet/{dietId}/dietComment/{dietCommentId}")
    public List<DietComment> deleteComment(@PathVariable Long dietId, @PathVariable Long dietCommentId){
        List<DietComment> dietCommentList;
        dietCommentList = dietCommentService.deleteDietComment(dietCommentId, dietId);
        return dietCommentList;
    }

    //댓글 수정
    @PutMapping("/diet/{dietId}/dietComment/{dietCommentId}")
    public List<DietComment> updateComment(@PathVariable Long dietId, @PathVariable Long dietCommentId, @RequestBody DietCommentRequestVo dietCommentRequestVo){
        List<DietComment> dietCommentList;
        dietCommentList = dietCommentService.updateDietComment(dietCommentRequestVo, dietCommentId, dietId);
        return  dietCommentList;
    }
    
    
}
