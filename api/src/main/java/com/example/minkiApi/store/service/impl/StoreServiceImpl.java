package com.example.minkiApi.store.service.impl;


import com.example.minkiApi.store.model.entity.StoreEntity;
import com.example.minkiApi.store.model.vo.AddStoreVo;
import com.example.minkiApi.store.repository.StoreRepository;
import com.example.minkiApi.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;

    //게시글 작성
    @Override
    @Transactional
    public String addStore(AddStoreVo addStoreVo) {
        String result;
        StoreEntity storeEntity = storeRepository.save(new StoreEntity(addStoreVo));
        if(null != storeEntity.getId() && !"".equals(storeEntity.getId())){
            result = "success";
        }else {
            result = "fail";
        }
        return result;
    }


    //게시글 수정
    @Transactional
    @Override
    public Optional<StoreEntity> updateStore(Long id, AddStoreVo addStoreVo) {
        Optional<StoreEntity> storeEntity = this.storeRepository.findById(id);
        storeEntity.ifPresent(t -> {
            if(addStoreVo.getStoreName() != null && !"".equals(addStoreVo.getStoreName())){
                t.setStoreName(addStoreVo.getStoreName());
            }
            if(addStoreVo.getMenu() != null && !"".equals(addStoreVo.getMenu())){
                t.setMenu(addStoreVo.getMenu());
            }
            if(addStoreVo.getTitle() != null && !"".equals(addStoreVo.getTitle())){
                t.setTitle(addStoreVo.getTitle());
            }
            if(addStoreVo.getContent() != null && !"".equals(addStoreVo.getContent())){
                t.setContent(addStoreVo.getContent());
            }
            if(addStoreVo.getAddress() != null && !"".equals(addStoreVo.getAddress())){
                t.setAddress(addStoreVo.getAddress());
            }
            if(addStoreVo.getTravelTime() != 0){
                t.setTravelTime(addStoreVo.getTravelTime());
            }
            if(addStoreVo.getCreatedDateTime() != null && !"".equals(addStoreVo.getCreatedDateTime())){
                t.setCreatedDateTime(addStoreVo.getCreatedDateTime());
            }
            this.storeRepository.save(t);
        });

        return storeEntity;
    }

    //게시글 삭제
    @Override
    @Transactional
    public String deleteStore(Long id) {
        Optional<StoreEntity> storeEntity = this.storeRepository.findById(id);
        storeEntity.ifPresent(t -> {
           storeRepository.deleteById(id);
        });
        return "delete success";
    }

    //게시글 조회
    @Override
    public Optional<StoreEntity> getStore(Long id) {
        Optional<StoreEntity> storeEntity = this.storeRepository.findById(id);
        return storeEntity;
    }

    //게시글 리스트 조회
    @Override
    public List<StoreEntity> getStoreList() {
        List<StoreEntity> storeList = this.storeRepository.findAllByOrderByIdDesc();
        return storeList;
    }

    //최대 storeId 조회
    @Override
    public Long getMaxStoreId() {
        Long response = this.storeRepository.getMaxStoreId();
        return response;
    }

    //포스팅 제목으로 리스트 조회
    @Override
    public List<StoreEntity> searchStoreByTitle(String title) {
        List<StoreEntity> storeList = this.storeRepository.findByTitleContaining(title);
        return storeList;
    }

    //가게 상호명으로 리스트 조회
    @Override
    public List<StoreEntity> searchStoreByStoreName(String storeName) {
        List<StoreEntity> storeList = this.storeRepository.findByStoreNameContaining(storeName);
        return storeList;
    }

    //포스팅 본문 내용으로 리스트 조회
    @Override
    public List<StoreEntity> searchStoreByContent(String content) {
        List<StoreEntity> storeList = this.storeRepository.findByContentContaining(content);
        return storeList;
    }

    //최다 추천(Best) 맛집
    @Override
    public StoreEntity getBestStore() {
        StoreEntity response = this.storeRepository.findTop1ByOrderByLikeCountDesc();
        return response;
    }

    //내가올린 맛집 포스팅 갯수
    @Override
    public Long getCountStoreByUserId(Long userId) {
        Long response;
        response = this.storeRepository.countByWriterId(userId);
        return response;
    }

}
