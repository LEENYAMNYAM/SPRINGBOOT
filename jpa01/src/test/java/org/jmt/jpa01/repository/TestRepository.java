package org.jmt.jpa01.repository;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.domain.Item;
import org.jmt.jpa01.domain.ItemSellStatus;
import org.jmt.jpa01.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TestRepository {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info(connection);
    }

    // MemberRepository

    @Test
    public void testfindName(){
        Member member = memberRepository.name("admin");
        log.info(member);
    }
    @Test
    public void testfindByUsername() {
    }

    @Test
    public void testInsert() throws SQLException{
        Member member = new Member();
        member.setName("admin");
        member.setPassword("123456");
        member.setEmail("admin@gamil.com");
        member.setAddr("부산");
        memberRepository.save(member);
    }

    @Test
    public void testFindAll() throws SQLException{
        List<Member> members = memberRepository.findAll();
        for(int i =0; i<members.size(); i++){
            log.info(members.get(i));
        }
    }

    @Test
    public void testFindById() throws SQLException{
        Member member = memberRepository.findById(3L).get();
        /* 3 만적으면 INTEGER이 되므로 LONG 형을 만들어 주려면 3L적어야함 */
        log.info(member);
    }

    @Test
    public void testUpdate() throws SQLException{
        Member member = memberRepository.findById(3L).get();
        member.setAddr("울산");
        member.setName("test3");
        memberRepository.save(member);
    }

    @Test
    public void testDelete() throws SQLException{
        memberRepository.deleteById(5L);
    }


    // ItemRepository /////////////////
    @Test
    public void testItemInsert() throws SQLException{
        Item item = Item.builder()
                /*builder() 사이에 적은 코드를 실행*/
                .itemNm("포도")
                .price(1000)
                .stockNumber(10)
                .itemSellStatus(ItemSellStatus.판매중)
                .itemDetail("밀양 꿀포도")
                .build();
        itemRepository.save(item);
    }

    @Test
    public void testItemFindAll() throws SQLException{
        List<Item> items = itemRepository.findAll();
        for(int i =0; i<items.size(); i++){
            log.info(items.get(i));
        }
    }

    @Test
    public void testItemFindById() throws SQLException{
        Item item = itemRepository.findById(3L).get();
        log.info(item);
    }

//    @Test
//    public void testItemUpdate() throws SQLException{
//        /* 객체로 가져올때는 Optional<Item> 형으로 가져와야함.*/
////      Optional<Item> item1 = itemRepository.findById(3L);
//        /* 지금은 get()을 사용해서 Item형을 바로 가져옴 */
//        Item item = itemRepository.findById(2L).get();
//        item.setItemNm("마늘");
//        item.setItemDetail("의성 마늘");
//        itemRepository.save(item);
//    }

    @Test
    public void testItemDelete() throws SQLException{
        itemRepository.deleteById(3L);
    }

    // Board/////////////////////////////////

    @Test
    public void TestBoardInsert(){
        Board board = new Board();
        board.setTitle("title");
        board.setContent("content");
        board.setAuthor("author");
        boardRepository.save(board);
    }




}
