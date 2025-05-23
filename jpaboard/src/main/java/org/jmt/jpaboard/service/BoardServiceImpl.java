package org.jmt.jpaboard.service;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.repository.BoardRepository;
import org.jmt.jpaboard.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public void registerBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = dtoToEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    @Override
    public BoardDTO readBoard(Long bno) {
        //BoardEntity boardEntity = boardRepository.findById(bno).orElse(null);
        BoardEntity boardEntity = boardRepository.findByIdWithImages(bno)
                .orElse(null);
        boardEntity.updateReadcount();
        boardRepository.save(boardEntity);
        return entityToDto(boardEntity);
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getBno()).orElse(null);
        boardEntity.change(boardDTO.getTitle(), boardDTO.getContent());
        if(boardDTO.getFileNames() != null) {
            boardEntity.removeImage();
            for (String filename : boardDTO.getFileNames()) {
                String[] arr = filename.split("_");
                boardEntity.addImage(arr[0], arr[1]);
            }
        }
        boardRepository.save(boardEntity);
    }

    /* @Transactional : 댓글과, 보드가 동시에 삭제 성공일때만 실행되도록 함. */
    @Transactional
    @Override
    public void deleteBoard(Long bno) {
        replyRepository.deleteByBoardEntity_Bno(bno);
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("bno");
        Page<BoardEntity> result = boardRepository.searchAll(
                pageRequestDTO.getTypes(),
                pageRequestDTO.getKeyword(),
                pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toList());

        /* 윗 부분과 동일함 */
//        List<BoardDTO> boardDTOs = new ArrayList<>();
//        List<BoardDTO> dtoList = result.getContent().forEach(BoardEntity boardEntity ->
//                boardDTOs.add(entityToDto(boardEntity)));

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
