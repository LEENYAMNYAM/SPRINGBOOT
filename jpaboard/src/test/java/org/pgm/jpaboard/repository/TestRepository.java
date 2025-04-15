package org.pgm.jpaboard.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.pgm.jpaboard.domain.BoardEntity;
import org.pgm.jpaboard.domain.ReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class TestRepository {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void testReplySave(){
        ReplyEntity reply = new ReplyEntity();
        reply.setReplyText("This is a test reply");
        reply.setAuthor("author");
        BoardEntity board = boardRepository.findById(1L).get();
        reply.setBoardEntity(board);
        replyRepository.save(reply);
    }
}
