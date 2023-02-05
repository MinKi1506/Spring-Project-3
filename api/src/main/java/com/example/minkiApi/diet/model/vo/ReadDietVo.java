package com.example.minkiApi.diet.model.vo;

import com.example.minkiApi.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadDietVo {

    private String title;

    private LocalDateTime createdDateTime;

    private String content;

    private String goal;

    private String period;

    private String penalty;

    private Long writerId;

    //Entity를 Vo화 시키는 생성자
    public ReadDietVo(DietEntity dietEntity){
        this.title = dietEntity.getTitle();
        this.createdDateTime = dietEntity.getCreatedDateTime();
        this.content = dietEntity.getContent();
        this.goal = dietEntity.getGoal();
        this.period = dietEntity.getPeriod();
        this.penalty = dietEntity.getPenalty();
        this.writerId = dietEntity.getWriterId();
    }


}
