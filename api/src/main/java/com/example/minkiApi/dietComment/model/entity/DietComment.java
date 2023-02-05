package com.example.minkiApi.dietComment.model.entity;

import com.example.minkiApi.diet.model.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DietComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="diet_id")
    @NotNull
    private DietEntity post;

    private String content;

    private Long writerId;
}
