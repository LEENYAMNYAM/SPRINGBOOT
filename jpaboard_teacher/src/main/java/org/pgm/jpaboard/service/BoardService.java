package org.pgm.jpaboard.service;

import org.pgm.jpaboard.domain.BoardEntity;
import org.pgm.jpaboard.dto.BoardDTO;
import org.pgm.jpaboard.dto.PageRequestDTO;
import org.pgm.jpaboard.dto.PageResponseDTO;
import org.pgm.jpaboard.repository.search.BoardSearch;

import java.util.List;
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
                String[] arr = fileName.split("_");
                boardEntity.addImage(arr[0], arr[1]);
            });
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
        List<String> fileNames=boardEntity.getImageSet().stream()
                .sorted()
                .map(img ->img.getUuid()+"_"+img.getFilename())
                .collect(Collectors.toList());
        boardDTO.setFileNames(fileNames);
        return boardDTO;
    }
}
