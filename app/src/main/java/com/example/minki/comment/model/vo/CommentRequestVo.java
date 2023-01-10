package com.example.minki.comment.model.vo;

import com.example.minki.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestVo {


    private StoreEntity post;
    private String content;
    private Long writerId;

}
