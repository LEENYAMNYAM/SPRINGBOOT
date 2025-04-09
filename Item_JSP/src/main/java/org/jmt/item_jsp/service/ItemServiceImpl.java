package org.jmt.item_jsp.service;

import org.jmt.item_jsp.dto.ItemDTO;
import org.jmt.item_jsp.domain.Item;
import org.jmt.item_jsp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public void registerItem(ItemDTO itemDTO) {
        Item item = dtoToEntity(itemDTO);
        itemRepository.save(item);
    }

    @Override
    public List<ItemDTO> readAllItem() {
        List<Item> result = itemRepository.findAll();
        List<ItemDTO> dtoList = result.stream()
                .map(item -> entityToDto(item))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ItemDTO readItem(Long id) {
        Item item = itemRepository.findById(id).get();
        ItemDTO itemDTO = entityToDto(item);
        return itemDTO;
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        Item item1 = itemRepository.findById(itemDTO.getId()).get();
        item1.change(itemDTO.getItemNm(),
                itemDTO.getPrice(),
                itemDTO.getStockNumber(),
                itemDTO.getItemDetail(),
                itemDTO.getItemSellStatus(),
                itemDTO.getMemo());
        itemRepository.save(item1);
    }

    @Override
    public void deleteItem(Long id) {

    }
}
