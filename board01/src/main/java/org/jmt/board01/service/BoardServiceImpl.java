package org.jmt.board01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.board01.dto.BoardDTO;
import org.jmt.board01.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/* Service 어노테이션은 Component로 부터 상속받은 친구들임 */
@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    /* 객체를 만들때 생성할 때 */
    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        return boardMapper.selectAll();
    }

    @Override
    public BoardDTO getBoard(int bno) {
        boardMapper.updateReadCount(bno);
        return boardMapper.selectByBno(bno);
    }

    @Override
    public void insertBoard(BoardDTO board) {
        boardMapper.insert(board);
    }

    @Override
    public void updateBoard(BoardDTO board) {
        boardMapper.updateByBno(board);
    }

    @Override
    public void deleteBoard(int bno) {
        boardMapper.deleteByBno(bno);
    }
}
