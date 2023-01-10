package com.example.minki.comment.model.entity;


import com.example.minki.store.model.entity.StoreEntity;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;

    private StoreEntity post;

    private String content;

    private Long writerId;



}
