package com.example.minkiApi.comment.model.entity;


import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.store.model.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="store_id")
    @NotNull
    private StoreEntity post;

    private String content;

    private Long writerId;



}
