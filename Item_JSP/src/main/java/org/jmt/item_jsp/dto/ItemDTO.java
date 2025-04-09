package org.jmt.item_jsp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jmt.item_jsp.domain.ItemSellStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;

    @NotEmpty
    private String itemNm;
    @NotEmpty
    private int price;
    @NotEmpty
    private int stockNumber;

    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private String memo;

    @Transient
    private String remark;

}
