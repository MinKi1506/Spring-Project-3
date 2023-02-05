package com.example.minkiApi.dietComment.model.vo;

import com.example.minkiApi.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietCommentRequestVo {

    @ManyToOne
    @JoinColumn(name="diet_id")
    @NotNull
    private DietEntity post;

    private String content;
    private Long writerId;
}
