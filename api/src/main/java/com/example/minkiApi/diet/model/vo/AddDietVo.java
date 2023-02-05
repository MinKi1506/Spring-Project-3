package com.example.minkiApi.diet.model.vo;

import com.example.minkiApi.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddDietVo {

    private String title;

    private LocalDateTime createdDateTime;

    private String content;

    private String goal;

    private String period;

    private String penalty;

    private Long writerId;

    //Entity를 Vo화 시키는 생성자
    public AddDietVo(DietEntity dietEntity){
        this.title = dietEntity.getTitle();
        this.createdDateTime = dietEntity.getCreatedDateTime();
        this.content = dietEntity.getContent();
        this.goal = dietEntity.getGoal();
        this.period = dietEntity.getPeriod();
        this.penalty = dietEntity.getPenalty();
        this.writerId = dietEntity.getWriterId();
    }
}

