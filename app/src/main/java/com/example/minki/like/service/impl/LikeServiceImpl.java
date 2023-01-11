package com.example.minki.like.service.impl;

import com.example.minki.like.model.vo.LikeRequestVo;
import com.example.minki.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    @Autowired
    private final WebClient webClient;

    @Override
    public int countLike(Long storeId) {
        int response = webClient.get()
                .uri("/store/"+storeId+"/like")
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
    }

    @Override
    @Transactional
    public int saveLike(LikeRequestVo likeRequestVo) {
        int response;
        response = webClient.post()
                .uri("/like")
                .bodyValue(likeRequestVo)
                .retrieve()
                .bodyToMono(int.class)
                .block();
        return response;
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
