package org.example.springboard.model;

import lombok.RequiredArgsConstructor;
import org.example.springboard.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/* 얘가 있어야 서비스객체가 만들어짐*/
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void insert(BoardDTO board) {
        boardRepository.dao_insert(board);
    }

    @Override
    public List<BoardDTO> boardList() {
        List<BoardDTO> boardList = boardRepository.dao_boardList();
        return boardList;
    }

    @Override
    public BoardDTO boardGet(int num) {
        BoardDTO board = boardRepository.dao_boardGet(num);
        return board;
    }

    @Override
    public void update(BoardDTO board) {
        boardRepository.dao_update(board);
    }

    @Override
    public void delete(int num) {
        boardRepository.dao_delete(num);
    }

    @Override
    public int count() {
        int count = boardRepository.dao_count();
        return count;
    }

    @Override
    public List<BoardDTO> searchList(HashMap<String, String> map) {
        List<BoardDTO> searchList = boardRepository.dao_searchList(map);
        return searchList;
    }

    @Override
    public int searchCount(HashMap<String, String> map) {
        int searchCount = boardRepository.dao_searchCount(map);
        return searchCount;
    }
}
