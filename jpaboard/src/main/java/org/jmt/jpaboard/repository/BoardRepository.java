package org.jmt.jpaboard.repository;

import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
}
