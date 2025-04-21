package org.jmt.securityex.service;

import org.jmt.securityex.domain.Board;
import org.jmt.securityex.domain.User;

import java.util.List;

public interface BoardService {
    void insert(Board board, User user);
    public List<Board> list();
    public Board findById(Long num);
    public void update(Board board, User user);
    public void delete(Long num);
}
