package org.jmt.jpaboard.service;

import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;

public interface BoardService {
    void registerBoard(BoardDTO boardDTO);
    BoardDTO readBoard(Long bno);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    default BoardEntity dtoToEntity(BoardDTO dto) {
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();
        return boardEntity;
    }
    default BoardDTO entityToDto(BoardEntity boardEntity) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(boardEntity.getBno())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .readcount(boardEntity.getReadcount())
                .regDate(boardEntity.getRegDate())
                .updateDate(boardEntity.getUpdateDate())
                .build();
        return boardDTO;
    }
}
