package com.example.minkiApi.dietFile.model.entity;

import com.example.minkiApi.dietFile.model.vo.DietMultipartVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String dietFile;

    private Long dietId;

    public DietFileEntity(DietMultipartVo dietMultipartVo){
        this.dietFile = dietMultipartVo.getDietFile().getOriginalFilename();
        this.dietId = dietMultipartVo.getDietId();
    }

}
