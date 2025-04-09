package org.jmt.jpaboard.service;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void registerBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = dtoToEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    @Override
    public BoardDTO readBoard(Long bno) {
        BoardEntity boardEntity = boardRepository.findById(bno).orElse(null);
        boardEntity.updateReadcount();
        boardRepository.save(boardEntity);
        return entityToDto(boardEntity);
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getBno()).orElse(null);
        boardEntity.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(boardEntity);
    }

    @Override
    public void deleteBoard(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("bno");
        Page<BoardEntity> result = boardRepository.searchAll(
                pageRequestDTO.getTypes(),
                pageRequestDTO.getKeyword(),
                pageable);

        List<BoardDTO> dtoList = result.stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
