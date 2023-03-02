package com.example.minkiApi.diet.service.impl;


import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.diet.model.vo.AddDietVo;
import com.example.minkiApi.diet.repository.DietRepository;
import com.example.minkiApi.diet.service.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class DietServiceImpl implements DietService {

    @Autowired
    private final DietRepository dietRepository;

    //게시글 작성
    @Override
    @Transactional
    public String addDiet(AddDietVo addDietVo) {
        String result;
        DietEntity dietEntity = dietRepository.save(new DietEntity(addDietVo));
        if(null != dietEntity.getId() && !"".equals(dietEntity.getId())){
            result = "success";
        }else {
            result = "fail";
        }
        return result;
    }


    //게시글 수정
    @Transactional
    @Override
    public Optional<DietEntity> updateDiet(Long id, AddDietVo addDietVo) {
        Optional<DietEntity> dietEntity = this.dietRepository.findById(id);
        dietEntity.ifPresent(t -> {
            if(addDietVo.getPeriod() != null && !"".equals(addDietVo.getPeriod())){
                t.setPeriod(addDietVo.getPeriod());
            }
            if(addDietVo.getGoal() != null && !"".equals(addDietVo.getGoal())){
                t.setGoal(addDietVo.getGoal());
            }
            if(addDietVo.getTitle() != null && !"".equals(addDietVo.getTitle())){
                t.setTitle(addDietVo.getTitle());
            }
            if(addDietVo.getContent() != null && !"".equals(addDietVo.getContent())){
                t.setContent(addDietVo.getContent());
            }
            if(addDietVo.getPenalty() != null && !"".equals(addDietVo.getPenalty())){
                t.setPenalty(addDietVo.getPenalty());
            }
            if(addDietVo.getCreatedDateTime() != null && !"".equals(addDietVo.getCreatedDateTime())){
                t.setCreatedDateTime(addDietVo.getCreatedDateTime());
            }
            this.dietRepository.save(t);
        });

        return dietEntity;
    }

    //게시글 삭제
    @Override
    @Transactional
    public String deleteDiet(Long id) {
        Optional<DietEntity> dietEntity = this.dietRepository.findById(id);
        dietEntity.ifPresent(t -> {
            dietRepository.deleteById(id);
        });
        return "delete success";
    }

    //게시글 조회
    @Override
    public Optional<DietEntity> getDiet(Long id) {
        Optional<DietEntity> dietEntity = this.dietRepository.findById(id);
        return dietEntity;
    }

    //게시글 리스트 조회
    @Override
    public List<DietEntity> getDietList() {
        List<DietEntity> dietList = this.dietRepository.findAllByOrderByIdDesc();
        return dietList;
    }

    //최대 dietId 조회
    @Override
    public Long getMaxDietId() {
        Long response = this.dietRepository.getMaxDietId();
        return response;
    }

    //포스팅 제목으로 리스트 조회
    @Override
    public List<DietEntity> searchDietByTitle(String title) {
        List<DietEntity> dietList = this.dietRepository.findByTitleContaining(title);
        return dietList;
    }

    //가게 상호명으로 리스트 조회
    @Override
    public List<DietEntity> searchDietByGoal(String goal) {
        List<DietEntity> dietList = this.dietRepository.findByGoalContaining(goal);
        return dietList;
    }

    //포스팅 본문 내용으로 리스트 조회
    @Override
    public List<DietEntity> searchDietByContent(String content) {
        List<DietEntity> dietList = this.dietRepository.findByContentContaining(content);
        return dietList;
    }

    //최다 추천(Best) 맛집
    @Override
    public DietEntity getBestDiet() {
        DietEntity response = this.dietRepository.findTop1ByOrderByCheerCountDesc();
        return response;
    }

    //내가올린 다이어트 포스팅 갯수
    @Override
    public Long getCountDietByUserId(Long userId) {
        Long response;
        response = this.dietRepository.countByWriterId(userId);
        return response;
    }
}
