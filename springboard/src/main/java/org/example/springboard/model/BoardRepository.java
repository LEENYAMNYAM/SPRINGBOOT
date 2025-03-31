package org.example.springboard.model;

import org.example.springboard.dto.BoardDTO;

import java.util.HashMap;
import java.util.List;

public interface BoardRepository {
    //추가
    void dao_insert(BoardDTO board);
    //전체보기
    List<BoardDTO> dao_boardList();
    //상세보기
    BoardDTO dao_boardGet(int num);
    //수정
    void dao_update(BoardDTO board);
    //삭제
    void dao_delete(int num);
    //갯수
    int dao_count();

    //검색
    List<BoardDTO> dao_searchList(HashMap<String, String> map);
    int dao_searchCount(HashMap<String, String> map);
}
