package org.example.springboard.model;

import org.example.springboard.dto.BoardDTO;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
    //추가
    void insert(BoardDTO board);
    //전체보기
    List<BoardDTO> boardList();
    //상세보기
    BoardDTO boardGet(int num);
    //수정
    void update(BoardDTO board);
    //삭제
    void delete(int num);
    //갯수
    int count();
    // 검색
    List<BoardDTO> searchList(HashMap<String, String> map);
    int searchCount(HashMap<String, String> map);
}
