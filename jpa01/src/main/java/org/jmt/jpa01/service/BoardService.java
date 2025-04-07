package org.jmt.jpa01.service;

import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {
    void registerBoard(BoardDTO boardDTO);
    BoardDTO readBoard(Long id);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(Long id);
    List<BoardDTO> readAllBoards();

    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .author(boardDTO.getAuthor())
                .build();
        return board;
    }
    default BoardDTO entityToDto(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .readcount(board.getReadcount())
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .build();
        return boardDTO;
    }
}
