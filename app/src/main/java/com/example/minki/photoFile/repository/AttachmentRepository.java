//package com.example.minki.photoFile.repository;
//
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface AttachmentRepository extends JpaRepository<Attachments, Long> {
//
//    //식당 id로 attachments 조회
//    public List<Attachments> findAllByStoreId(Long storeId);
//
//
//    //delete file list로 attachments 삭제
//    @Modifying
//    @Query(value = "DELETE FROM Attachments a " +
//            "WHERE a.id IN (:deleteFileList)")
//    public void deleteByAttachIdList(@Param("deleteFileList") List<Long> deleteFileList);
//}
