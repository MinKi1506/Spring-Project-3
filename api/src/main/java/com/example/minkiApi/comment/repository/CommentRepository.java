package com.example.minkiApi.comment.repository;

import com.example.minkiApi.comment.model.entity.Comment;
import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //앞단에서 받은 storeId로 storeEntity를 검색하여 해당 포스팅의 댓글 전체 조회하기
    List<Comment> findAllByPost(StoreEntity storeEntity);

    //앞단에서 받은 storeId로 storeEntity를 검색하여 해당 포스팅의 댓글 갯수 조회하기
    Long countByPost(StoreEntity storeEntity);


//    @Query("SELECT c from Comment c where c.post.id=:postNo and c.id>0 order by c.id ASC ")
//    public List<Comment> getCommentsOfPost(@Param("postNo") Long postNo);
}
