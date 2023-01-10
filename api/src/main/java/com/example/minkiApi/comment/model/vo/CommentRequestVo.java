package com.example.minkiApi.comment.model.vo;

import com.example.minkiApi.store.model.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestVo {


    @ManyToOne
    @JoinColumn(name="store_id")
    @NotNull
    private StoreEntity post;

    private String content;
    private Long writerId;

}
