package com.example.minki.comment.model.vo;

import com.example.minki.diet.model.entity.DietEntity;
import com.example.minki.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestVo {

    private DietEntity post;
    private String content;
    private Long writerId;

}
