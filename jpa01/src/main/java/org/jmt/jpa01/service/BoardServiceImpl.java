package org.jmt.jpa01.service;

import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.dto.BoardDTO;
import org.jmt.jpa01.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<BoardDTO> readAllBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDTO> boardDTOs = boards.stream()
                .map(board -> entityToDto(board))
                .collect(Collectors.toList());
        /* 아래랑 같은 의미임 */
//        List<BoardDTO> boardDTOList = new ArrayList<>();
//        for (Board board : boards){
//            boardDTOList.add(entityToDto(board));
//        }
        return boardDTOs;
    }
}
