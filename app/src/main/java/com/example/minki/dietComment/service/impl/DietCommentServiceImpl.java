package com.example.minki.dietComment.service.impl;



import com.example.minki.dietComment.model.entity.DietComment;
import com.example.minki.dietComment.model.vo.DietCommentRequestVo;
import com.example.minki.dietComment.service.DietCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietCommentServiceImpl implements DietCommentService {

    @Autowired
    WebClient webClient;

    // 댓글 등록
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String createDietComment(@PathVariable Long dietId, DietCommentRequestVo dietCommentRequestVo) {
        String response;
        response = webClient.post()
                .uri("/diet/"+dietId+"/dietComment")
                .bodyValue(dietCommentRequestVo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    //댓글 리스트 조회
    @Override
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<DietComment> getDietCommentList(@PathVariable Long dietId) {
        List<DietComment> dietCommentList;
        dietCommentList = webClient.get()
                .uri("/diet/"+dietId+"/dietComment")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DietComment.class)
                .toStream()
                .collect(Collectors.toList());
        return dietCommentList;
    }

    // 댓글 삭제
    @Transactional
    @Override
    public List<DietComment> deleteDietComment(Long dietCommentId, Long dietId) {
        List<DietComment> dietCommentList;
        dietCommentList = webClient.delete()
                .uri("/diet/"+dietId+"/dietComment/"+dietCommentId)
                .retrieve()
                .bodyToFlux(DietComment.class)
                .toStream()
                .collect(Collectors.toList());
        return dietCommentList;
    }

    // 댓글 수정
    @Override
    public List<DietComment> updateDietComment(DietCommentRequestVo dietCommentRequestVo, Long dietCommentId, Long dietId) {
        List<DietComment> dietCommentList;
        dietCommentList = webClient.put()
                .uri("/diet/"+dietId+"/dietComment/"+dietCommentId)
                .bodyValue(dietCommentRequestVo)
                .retrieve()
                .bodyToFlux(DietComment.class)
                .toStream()
                .collect(Collectors.toList());
        return dietCommentList;
    }



}
