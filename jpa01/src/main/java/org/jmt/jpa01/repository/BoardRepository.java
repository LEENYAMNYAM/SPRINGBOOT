package org.jmt.jpa01.repository;

import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    // select * from tbl_board where title like %keyword%;
//    @Query("select b from Board b where b.title like concat('%', :keyword ,'%') ")
//    Page<Board> searchTitle(String keyword, Pageable pageable);

}
