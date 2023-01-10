package com.example.minkiApi.comment.repository;

import com.example.minkiApi.comment.model.entity.Comment;
import com.example.minkiApi.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(StoreEntity storeEntity);

//    @Query("SELECT c from Comment c where c.post.id=:postNo and c.id>0 order by c.id ASC ")
//    public List<Comment> getCommentsOfPost(@Param("postNo") Long postNo);
}
