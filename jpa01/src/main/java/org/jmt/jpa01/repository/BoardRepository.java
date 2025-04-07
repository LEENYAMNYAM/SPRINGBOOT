package org.jmt.jpa01.repository;

import org.jmt.jpa01.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
