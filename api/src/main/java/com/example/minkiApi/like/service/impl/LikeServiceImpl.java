package com.example.minkiApi.like.service.impl;

import com.example.minkiApi.like.model.entity.LikeEntity;
import com.example.minkiApi.like.model.vo.LikeRequestVo;
import com.example.minkiApi.like.repository.LikeRepository;
import com.example.minkiApi.like.service.LikeService;
import com.example.minkiApi.login.model.entity.UserEntity;
import com.example.minkiApi.login.repository.UserRepository;
import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final StoreRepository storeRepository;
    @Autowired
    private final LikeRepository likeRepository;

    @Override
    public int countLike(Long storeId, Long userId) {

        //저장된 Data가 있다면 1, 없다면 0
        Optional<LikeEntity> findLike = likeRepository.findByStoreEntity_IdAndUserEntity_Id(storeId, userId);
        if(findLike.isPresent()){
            return 1;
        }else {
            return 0;
        }
    }


    @Override
    @Transactional
    public int saveLike(Long storeId, Long userId) {
        Optional<LikeEntity> findLike = likeRepository.findByStoreEntity_IdAndUserEntity_Id(storeId,userId);
        System.out.println("좋아요가 있는지"+findLike.isPresent());

        if(findLike.isPresent()){
            likeCountMinus(storeId);
            likeRepository.deleteByStoreEntity_IdAndUserEntity_Id(storeId,userId);
//            storeRepository.minusLike(storeId); 좋아요 하나 감소
            return 0;
        } else {
            UserEntity userEntity = userRepository.findById(userId).get();
            StoreEntity storeEntity = storeRepository.findById(storeId).get();

            LikeEntity likeEntity = LikeEntity.toLikeEntity(storeEntity, userEntity);
            likeCountPlus(storeId);
            likeRepository.save(likeEntity);
//            storeRepository.plusLike(storeId); 좋아요 하나 증가
            return 1;
        }
    }

    //포스팅 좋아요수 증가
    public void likeCountPlus(Long storeId) {
        Long response = storeRepository.findById(storeId).get().getLikeCount();
        response++;
        StoreEntity storeEntity;
        storeEntity = storeRepository.findById(storeId).get();
        storeEntity.setLikeCount(response);
        storeRepository.save(storeEntity);

    }

    //포스팅 좋아요수 감소
    public void likeCountMinus(Long storeId) {
        Long response = storeRepository.findById(storeId).get().getLikeCount();
        response--;
        StoreEntity storeEntity;
        storeEntity = storeRepository.findById(storeId).get();
        storeEntity.setLikeCount(response);
        storeRepository.save(storeEntity);
    }


    //@Override
//public int findLike(Long storeId, Long userId) {
//
//    //저장된 Data가 있다면 1, 없다면 0
//    Optional<LikeEntity> findLike = likeRepository.findByStoreEntityIdAndUserEntityId(storeId, userId);
//    if(findLike.isPresent()){
//        return 1;
//    }else {
//        return 0;
//    }
//}
//@Override
//@Transactional
//public int saveLike(Long storeId, Long userId) {
//    Optional<LikeEntity> findLike = likeRepository.findByStoreEntityIdAndUserEntityId(storeId,userId);
//    System.out.println("좋아요가 있는지"+findLike.isPresent());
//
//    if(findLike.isPresent()){
//        likeRepository.deleteByStoreEntityIdAndUserEntityId(storeId,userId);
////            storeRepository.minusLike(storeId); 좋아요 하나 감소
//        return 0;
//    } else {
//        UserEntity userEntity = userRepository.findById(userId).get();
//        StoreEntity storeEntity = storeRepository.findById(storeId).get();
//
//        LikeEntity likeEntity = LikeEntity.toLikeEntity(storeEntity, userEntity);
//        likeRepository.save(likeEntity);
////            storeRepository.plusLike(storeId); 좋아요 하나 증가
//        return 1;
//    }
//}
}
