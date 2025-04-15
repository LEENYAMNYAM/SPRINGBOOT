package org.pgm.jpaboard.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.pgm.jpaboard.domain.BoardEntity;
import org.pgm.jpaboard.domain.QBoardEntity;
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

        if((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type : types) {
                switch(type) {
                    case "t":
                        builder.or(boardEntity.title.contains(keyword));
                        break;
                    case "c":
                        builder.or(boardEntity.content.contains(keyword));
                        break;
                    case "w":
                        builder.or(boardEntity.author.contains(keyword));
                }
            }
            query.where(builder);
        }
        query.where(boardEntity.bno.gt(0l));
        this.getQuerydsl().applyPagination(pageable, query);
        List<BoardEntity> list=query.fetch();
        long count=query.fetchCount();
        return new PageImpl<BoardEntity>(list, pageable, count);
    }
}
