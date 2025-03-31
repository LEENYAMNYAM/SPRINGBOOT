package org.jmt.board01.service;

import org.jmt.board01.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getList();
    BoardDTO getBoard(int bno);
    void insertBoard(BoardDTO board);
    void updateBoard(BoardDTO board);
    void deleteBoard(int bno);

}
