package org.jmt.jpa01.service;

import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.dto.BoardDTO;
import org.jmt.jpa01.dto.PageRequestDTO;
import org.jmt.jpa01.dto.PageResponseDTO;
import org.jmt.jpa01.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void registerBoard(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
    }

    @Override
    public BoardDTO readBoard(Long bno) {
        Board board = boardRepository.findById(bno).get();
        board.updateReadcount();
        boardRepository.save(board);
        BoardDTO boardDTO = entityToDto(board);
        return boardDTO;
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        Board board1 = boardRepository.findById(boardDTO.getBno()).get();
        /* change 함수 활용법*/
        board1.change(boardDTO.getTitle(), boardDTO.getContent());
        /* 따로 함수를 안만들었을때 방법 */
//        board1.setContent(boardDTO.getContent());
//        board1.setAuthor(boardDTO.getAuthor());
//        board1.setTitle(boardDTO.getTitle());
        boardRepository.save(board1);
    }

    @Override
    public void deleteBoard(Long bno) {
        boardRepository.deleteById(bno);
    }

    /* 페이징 있을 시 */
    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("bno");

        /* 검색 없을 때 */
//        Page<Board> result = boardRepository.findAll(pageable);
        /* 검색 기능 추가 */
//        Page<Board> result = boardRepository.searchTitle(pageRequestDTO.getKeyword(), pageable);
        Page<Board> result = boardRepository.searchAll(
                pageRequestDTO.getTypes(),
                pageRequestDTO.getKeyword(),
                pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> entityToDto(board))
                .collect(Collectors.toList());
        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    /* 페이징이 없을 시 */
//    @Override
//    public List<BoardDTO> readAllBoards() {
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("bno").descending());
//        List<Board> boards = boardRepository.findAll(pageable).getContent();
//        List<BoardDTO> boardDTOs = boards.stream()
//                .map(board -> entityToDto(board))
//                .collect(Collectors.toList());
//        /* 아래랑 같은 의미임 */
////        List<BoardDTO> boardDTOList = new ArrayList<>();
////        for (Board board : boards){
////            boardDTOList.add(entityToDto(board));
////        }
//        return boardDTOs;
//    }
}
