package com.example.minkiApi.cheer.service.impl;

import com.example.minkiApi.cheer.repository.CheerRepository;
import com.example.minkiApi.cheer.model.entity.CheerEntity;
import com.example.minkiApi.cheer.service.CheerService;
import com.example.minkiApi.diet.model.entity.DietEntity;
import com.example.minkiApi.diet.repository.DietRepository;
import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheerServiceImpl implements CheerService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DietRepository dietRepository;
    @Autowired
    private final CheerRepository cheerRepository;

    @Override
    public int countCheer(Long dietId, Long userId) {

        //저장된 Data가 있다면 1, 없다면 0
        Optional<CheerEntity> findCheer = cheerRepository.findByDietEntity_IdAndUserEntity_Id(dietId, userId);
        if(findCheer.isPresent()){
            return 1;
        }else {
            return 0;
        }
    }

    //맛집 포스팅 좋아요
    @Override
    @Transactional
    public int saveCheer(Long dietId, Long userId) {
        Optional<CheerEntity> findCheer = cheerRepository.findByDietEntity_IdAndUserEntity_Id(dietId,userId);
        System.out.println("좋아요가 있는지"+findCheer.isPresent());

        if(findCheer.isPresent()){
            cheerCountMinus(dietId);
            cheerRepository.deleteByDietEntity_IdAndUserEntity_Id(dietId,userId);
//            dietRepository.minusCheer(dietId); 좋아요 하나 감소
            return 0;
        } else {
            UserEntity userEntity = userRepository.findById(userId).get();
            DietEntity dietEntity = dietRepository.findById(dietId).get();

            CheerEntity cheerEntity = CheerEntity.toCheerEntity(dietEntity, userEntity);
            cheerCountPlus(dietId);
            cheerRepository.save(cheerEntity);
//            dietRepository.plusCheer(dietId); 좋아요 하나 증가
            return 1;
        }
    }


    //포스팅 좋아요수 증가
    public void cheerCountPlus(Long dietId) {
        Long response = dietRepository.findById(dietId).get().getCheerCount();
        response++;
        DietEntity dietEntity;
        dietEntity = dietRepository.findById(dietId).get();
        dietEntity.setCheerCount(response);
        dietRepository.save(dietEntity);

    }

    //포스팅 좋아요수 감소
    public void cheerCountMinus(Long dietId) {
        Long response = dietRepository.findById(dietId).get().getCheerCount();
        response--;
        DietEntity dietEntity;
        dietEntity = dietRepository.findById(dietId).get();
        dietEntity.setCheerCount(response);
        dietRepository.save(dietEntity);
    }

    //유저가 누른 좋아요 갯수
    @Override
    public Long userCheerCount(Long userId) {
        Long response;
        UserEntity userEntity = userRepository.findById(userId).get();
        response = cheerRepository.countByUserEntity_Id(userId);
        return response;
    }


    //@Override
//public int findCheer(Long dietId, Long userId) {
//
//    //저장된 Data가 있다면 1, 없다면 0
//    Optional<CheerEntity> findCheer = cheerRepository.findByDietEntityIdAndUserEntityId(dietId, userId);
//    if(findCheer.isPresent()){
//        return 1;
//    }else {
//        return 0;
//    }
//}
//@Override
//@Transactional
//public int saveCheer(Long dietId, Long userId) {
//    Optional<CheerEntity> findCheer = cheerRepository.findByDietEntityIdAndUserEntityId(dietId,userId);
//    System.out.println("좋아요가 있는지"+findCheer.isPresent());
//
//    if(findCheer.isPresent()){
//        cheerRepository.deleteByDietEntityIdAndUserEntityId(dietId,userId);
////            dietRepository.minusCheer(dietId); 좋아요 하나 감소
//        return 0;
//    } else {
//        UserEntity userEntity = userRepository.findById(userId).get();
//        DietEntity dietEntity = dietRepository.findById(dietId).get();
//
//        CheerEntity cheerEntity = CheerEntity.toCheerEntity(dietEntity, userEntity);
//        cheerRepository.save(cheerEntity);
////            dietRepository.plusCheer(dietId); 좋아요 하나 증가
//        return 1;
//    }
//}
}
