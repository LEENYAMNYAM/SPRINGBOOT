package org.jmt.item_jsp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_item")
@Getter @Setter @ToString @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String itemNm;

    @Column(nullable = false)
    @ColumnDefault("1000")
    private int price;

    @Column(columnDefinition = "int default 10 not null")
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regTime;

    @UpdateTimestamp                                             
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String memo;

    @Transient
    private String remark;

    public void change(String itemNm, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus, String memo) {
        this.itemNm = itemNm;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
        this.memo = memo;
    }


}
