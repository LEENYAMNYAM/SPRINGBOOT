package org.jmt.jpaboard.service;

import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.domain.BoardImage;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;

import java.util.stream.Collectors;

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
        if(dto.getFileNames() != null) {
            dto.getFileNames().forEach(fileName -> {
                Strign[] arr = fileName.splite("_");
                /* arr[0] : uuid , arr[1] : filename*/
                boardEntity.addImage(arr[0], arr[1]);
            })
        }
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
        List<String> fileNames = boardEntity.getImageSet().stream()
                .sorted()
                .map( BoardImage img -> img.getUuid() + "_" + img.getFilename())
                .collect(Collectors.toList());
        boardDTO.setFileNames(fileNames);

        return boardDTO;
    }
}
