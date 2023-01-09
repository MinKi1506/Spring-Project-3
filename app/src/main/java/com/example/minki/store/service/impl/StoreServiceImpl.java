package com.example.minki.store.service.impl;

import com.example.minki.store.model.entity.StoreEntity;
import com.example.minki.store.model.vo.AddStoreVo;
import com.example.minki.store.repository.StoreRepository;
import com.example.minki.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    //게시글 작성
    @Override
    @Transactional
    public String addStore(@RequestBody AddStoreVo addStoreVo) {
        String result;
        StoreEntity storeEntity = storeRepository.save(new StoreEntity(addStoreVo));
        if(null != storeEntity.getId() && !"".equals(storeEntity.getId())){
            result = "success";
        }else {
            result = "fail";
        }return result;
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
            if(addStoreVo.getEditDate() != null && !"".equals(addStoreVo.getEditDate())){
                t.setEditDate(addStoreVo.getEditDate());
            }
            this.storeRepository.save(t);
        });

        return storeEntity;
    }

    //게시글 삭제
    @Override
    public String deleteStore(Long id) {
        Optional<StoreEntity> storeEntity = this.storeRepository.findById(id);
        storeEntity.ifPresent(t -> {
           storeRepository.deleteById(id);
        });
        return "delete success";
    }

//    //게시글 수정
//    @Transactional
//    public String updateStore(Long id, final AddStoreVo addStoreVo){
//        Optional<StoreEntity> storeEntity = storeRepository.findById(id);
//        storeEntity.ifPresent(selectUser);
//
//        if(storeEntity.isPresent()){
//            return "fail";
//        }
//        StoreEntity newStoreEntity = newStoreEntity(addStoreVo);
//        return "success";
//    }
}
