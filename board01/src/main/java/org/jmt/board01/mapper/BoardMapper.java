package org.jmt.board01.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jmt.board01.dto.BoardDTO;

import java.util.List;

@Mapper
public interface BoardMapper {

//    Mapper는 리턴값을 하나만 가져옴(2개 안됨)

    //전체보기
    List<BoardDTO> selectAll();

    //추가하기
    void insert(BoardDTO board);

    //상세보기
    BoardDTO selectByBno(int bno);

    //삭제하기
    void deleteByBno(int bno);

    // 수정하기
    void updateByBno(BoardDTO board);

}
