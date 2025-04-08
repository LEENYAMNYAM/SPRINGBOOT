package org.jmt.item_jsp.service;

import org.jmt.item_jsp.ItemDTO;
import org.jmt.item_jsp.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ItemService {
    void registerItem(ItemDTO itemDTO);
    List<ItemDTO> readAllItem();
    ItemDTO readItem(Long id);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(Long id);

    default Item dtoToEntity(ItemDTO itemDTO){
        Item item  = Item.builder()
                .id(itemDTO.getId())
                .itemNm(itemDTO.getItemNm())
                .price(itemDTO.getPrice())
                .stockNumber(itemDTO.getStockNumber())
                .itemDetail(itemDTO.getItemDetail())
                .itemSellStatus(itemDTO.getItemSellStatus())
                .memo(itemDTO.getMemo())
                .build();
        return item;
    }
    default ItemDTO entityToDto(Item item){
        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .itemNm(item.getItemNm())
                .price(item.getPrice())
                .stockNumber(item.getStockNumber())
                .itemDetail(item.getItemDetail())
                .itemSellStatus(item.getItemSellStatus())
                .regTime(item.getRegTime())
                .updateTime(item.getUpdateTime())
                .memo(item.getMemo())
                .build();
        return itemDTO;
    }

}
