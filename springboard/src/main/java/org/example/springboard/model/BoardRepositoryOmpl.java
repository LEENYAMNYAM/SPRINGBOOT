package org.example.springboard.model;

import lombok.RequiredArgsConstructor;
import org.example.springboard.dto.BoardDTO;
import org.example.springboard.mapper.BoardMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryOmpl implements BoardRepository {

    private final BoardMapper boardMapper;

    @Override
    public void dao_insert(BoardDTO board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public List<BoardDTO> dao_boardList() {
        List<BoardDTO> dao_boardList = boardMapper.boardList();
        return dao_boardList;
    }

    @Override
    public BoardDTO dao_boardGet(int num) {
        BoardDTO board = boardMapper.getBoard(num);
        return board;
    }

    @Override
    public void dao_update(BoardDTO board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void dao_delete(int num) {
        boardMapper.deleteBoard(num);
    }

    @Override
    public int dao_count() {
        int dao_count = boardMapper.countBoard();
        return dao_count;
    }

    @Override
    public List<BoardDTO> dao_searchList(HashMap<String, String> map) {
        List<BoardDTO> dao_searchList = boardMapper.searchList(map);
        return dao_searchList;
    }

    @Override
    public int dao_searchCount(HashMap<String, String> map) {
        int dao_searchCount = boardMapper.searchCount(map);
        return dao_searchCount;
    }
}
