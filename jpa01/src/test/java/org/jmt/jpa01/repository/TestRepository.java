package org.jmt.jpa01.repository;

import lombok.extern.log4j.Log4j2;
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

@SpringBootTest
@Log4j2
public class TestRepository {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.info(connection);
    }

    // MemberRepository
    @Test
    public void testInsert() throws SQLException{
        Member member = new Member();
        member.setName("test2");
        member.setPassword("123456");
        member.setEmail("test2@gamil.com");
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


    // ItemRepository
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

    @Test
    public void testItemUpdate() throws SQLException{
        Item item = itemRepository.findById(2L).get();
        item.setItemNm("배");
        item.setPrice(1000);
        item.setStockNumber(10);
        item.setItemDetail("나주 꿀배");
        itemRepository.save(item);
    }

    @Test
    public void testItemDelete() throws SQLException{
        itemRepository.deleteById(3L);
    }

}
