package org.jmt.jpa01.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* 상품코드 */
    private Long id;

    @Column(nullable = false, length = 50)
    /* 상품명 */
    private String itemNm;

    @Column(name="price", nullable = false)
    @ColumnDefault("1000")
    /* 가격 */
    private int price;


    @Column(columnDefinition = "int default '10' not null")
    /* 재고수량 */
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    /* 상품 상세 설명 */
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    /* 상품 판매 상태
    * EnuMType을 STRRING로 하면 STRING값(판매중, 판매완료, 입고대기)이 DB에 들어감
    * EnumType을 ORIGINAL로 하면 INDEX(0,1,2...)값이 DB에 들어감 */
    private ItemSellStatus itemSellStatus;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regTime;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime updateTime;
    //@Transient
    private String memo;
    @Transient
    private String remark;

}
