package org.jmt.jpaboard.repository;

import org.jmt.jpaboard.domain.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    @Query("select r from ReplyEntity r where r.boardEntity.bno=:bno")
    Page<ReplyEntity> listOfBoard(Long bno, Pageable pageable);

    void deleteByBoardEntity_Bno(Long bno);


}
