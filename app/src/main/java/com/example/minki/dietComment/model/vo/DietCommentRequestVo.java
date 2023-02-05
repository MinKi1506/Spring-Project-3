package com.example.minki.dietComment.model.vo;

import com.example.minki.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietCommentRequestVo {


    @NotNull
    private DietEntity post;

    private String content;
    private Long writerId;
}
