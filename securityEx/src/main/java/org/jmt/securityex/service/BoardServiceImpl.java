package org.jmt.securityex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.securityex.domain.Board;
import org.jmt.securityex.domain.User;
import org.jmt.securityex.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void insert(Board board, User user) {
        board.setUser(user);
        boardRepository.save(board);
    }

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Board findById(Long num) {
        Board board = boardRepository.findById(num).orElse(null);
        if (board == null){
            return null;
        }
        board.updateHitCount();
        boardRepository.save(board);
        return board;
    }

    @Override
    public void update(Board board, User user) {
        Board oldBoard = boardRepository.findById(board.getNum()).orElse(null);
        oldBoard.setUser(user);
        oldBoard.change(board.getTitle(), board.getContent());
        boardRepository.save(oldBoard);
        /* 객체를 새로 만들어서 하지 않으면 없는 정보들이 모두 null이 되기 때문에 이렇게 객체를 만들어서 저장함. */
    }

    @Override
    public void delete(Long num) {
        boardRepository.deleteById(num);
    }
}
