package com.example.minki.diet.model.entity;


import com.example.minki.diet.model.vo.AddDietVo;
import com.example.minki.diet.model.vo.ReadDietVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietEntity {


    private Long id;

    private String title;

    private LocalDateTime createdDateTime;

    private String content;

    private String goal;

    private String period;

    private String penalty;

    private Long cheerCount = 0L;

    private Long dietCommentCount = 0L;

    private Long writerId;

    @Builder
    public DietEntity(String title, LocalDateTime createdDateTime, String content, String goal, String period, String penalty, Long writerId){
        this.title = title;
        this.createdDateTime = createdDateTime;
        this.content = content;
        this.goal = goal;
        this.period = period;
        this.penalty = penalty;
        this.cheerCount = 0L;
        this.dietCommentCount = 0L;
        this.writerId = writerId;
    }

    //request VO를 Entity화 시키는 생성자
    public  DietEntity(AddDietVo addDietVo){
        this.title = addDietVo.getTitle();
        this.createdDateTime = addDietVo.getCreatedDateTime();
        this.content = addDietVo.getContent();
        this.goal = addDietVo.getGoal();
        this.period = addDietVo.getPeriod();
        this.penalty = addDietVo.getPenalty();
        this.writerId = addDietVo.getWriterId();
    }

    //response VO를 Entity화 시키는 생성자
    public  DietEntity(ReadDietVo readDietVo){
        this.title = readDietVo.getTitle();
        this.createdDateTime = readDietVo.getCreatedDateTime();
        this.content = readDietVo.getContent();
        this.goal = readDietVo.getGoal();
        this.period = readDietVo.getPeriod();
        this.penalty = readDietVo.getPenalty();
        this.writerId = readDietVo.getWriterId();
    }





}
