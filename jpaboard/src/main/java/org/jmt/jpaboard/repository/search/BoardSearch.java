package org.jmt.jpaboard.repository.search;

import org.jmt.jpaboard.domain.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardEntity> searchAll(String[] types, String keyword, Pageable pageable);
}
