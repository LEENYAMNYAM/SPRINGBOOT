package org.jmt.jpaboard.repository;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpaboard.domain.BoardEntity;
import org.jmt.jpaboard.domain.ReplyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class TestRepository {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testReplySave() {
        ReplyEntity reply = new ReplyEntity();
        reply.setReplyText("This is a test reply");
        reply.setAuthor("Author");
        BoardEntity board = boardRepository.findById(1L).get();
        reply.setBoardEntity(board);
        replyRepository.save(reply);
    }
}
