package org.jmt.jpaboard.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoardEntity boardEntity = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(boardEntity);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type : types) {
                switch(type){
                    case "t":
                        builder.or(boardEntity.title.contains(keyword));
                        break;
                    case "c":
                        builder.or(boardEntity.content.contains(keyword));
                        break;
                    case "w":
                        builder.or(boardEntity.author.contains(keyword));
                        break;
                }
            }
            query.where(builder);
        }
        query.where(boardEntity.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<BoardEntity> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
