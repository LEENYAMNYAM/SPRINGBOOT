package org.jmt.board01;

import lombok.extern.log4j.Log4j2;
import org.jmt.board01.dto.BoardDTO;
import org.jmt.board01.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Log4j2
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testDataSource() throws SQLException {
        Connection conn = dataSource.getConnection();
        log.info("1111111111"+conn);
    }

    @Test
    public void insertTest() throws SQLException {
        BoardDTO dto = new BoardDTO();
        dto.setTitle("title22");
        dto.setContent("content22");
        dto.setAuthor("author22");
        boardMapper.insert(dto);
    }

    @Test
    public void selectALLTest() throws SQLException {
        List<BoardDTO> list = boardMapper.selectAll();
        for (BoardDTO dto : list) {
            log.info(dto);
        }
    }
}
