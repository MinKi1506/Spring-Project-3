package com.example.minkiApi.diet.controller;

import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.diet.model.vo.AddDietVo;
import com.example.minkiApi.diet.service.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DietController {

    @Autowired
    private final DietService dietService;

    //게시글 작성
    @PostMapping("/addDiet")
    public String addDiet(@RequestBody AddDietVo addDietVo){
        dietService.addDiet(addDietVo);
        return "added";
    }

    //게시글 수정
    @PutMapping("/updateDiet/{id}")
    public String updateDiet(@PathVariable Long id, @RequestBody AddDietVo addDietVo){
        Optional<DietEntity> dietEntity = this.dietService.updateDiet(id, addDietVo);
        return "updated";
    }

    //게시글 목록 불러오기
    @GetMapping("/getDietList")
    public List<DietEntity> getDietList(){
        List<DietEntity> dietList;
        dietList = dietService.getDietList();
        return dietList;
    }


    //게시글 하나 불러오기
    @GetMapping("/getDiet/{id}")
    public Optional<DietEntity> getDiet(@PathVariable Long id){
        Optional<DietEntity> dietEntity = dietService.getDiet(id);
        return dietEntity;
    }


    //게시글 삭제
    @DeleteMapping("/deleteDiet/{id}")
    public String deleteDiet(@PathVariable Long id){
        dietService.deleteDiet(id);
        return "deleted";
    }


    //최대 DietId 조회
    @GetMapping("/getMaxDietId")
    public Long getMaxDietId(){
        Long response = dietService.getMaxDietId();
        return response;
    }

    //제목으로 리스트 검색
    @GetMapping("/searchDietByTitle/{title}")
    public List<DietEntity> searchDietByTitle(@PathVariable String title) {
        List<DietEntity> dietList;
        dietList = dietService.searchDietByTitle(title);
        return dietList;
    }

    //가게명으로 리스트 검색
    @GetMapping("/searchDietByGoal/{goal}")
    public List<DietEntity> searchDietByGoal(@PathVariable String goal) {
        List<DietEntity> dietList;
        dietList = dietService.searchDietByGoal(goal);
        return dietList;
    }

    //본문 내용으로 리스트 검색
    @GetMapping("/searchDietByContent/{content}")
    public List<DietEntity> searchDietByContent(@PathVariable String content) {
        List<DietEntity> dietList;
        dietList = dietService.searchDietByContent(content);
        return dietList;
    }


    //최다 추천(Best) 맛집
    @GetMapping("/getBestDiet")
    public DietEntity getBestDiet(){
        DietEntity response;
        response = dietService.getBestDiet();
        return  response;
    }

    //내가올린 다이어트 포스팅 갯수
    @GetMapping("/getCountDiet/{userId}")
    public Long getCountDietByUserId(@PathVariable Long userId){
        Long response;
        response = dietService.getCountDietByUserId(userId);
        return  response;
    }

    //내가올린 다이어트 포스팅 갯수
}
