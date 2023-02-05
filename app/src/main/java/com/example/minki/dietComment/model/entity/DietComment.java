package com.example.minki.dietComment.model.entity;

import com.example.minki.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietComment {


    private Long id;

    private DietEntity post;

    private String content;

    private Long writerId;
}
