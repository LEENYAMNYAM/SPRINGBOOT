package org.pgm.jpaboard.repository;

import jakarta.persistence.Entity;
import org.pgm.jpaboard.domain.BoardEntity;
import org.pgm.jpaboard.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from BoardEntity b where b.bno=:bno")
    Optional<BoardEntity> findByIdWithImages(Long bno);
}
