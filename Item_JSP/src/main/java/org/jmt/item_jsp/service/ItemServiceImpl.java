package org.jmt.item_jsp.service;

import org.jmt.item_jsp.ItemDTO;
import org.jmt.item_jsp.domain.Item;
import org.jmt.item_jsp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return List.of();
    }

    @Override
    public ItemDTO readItem(Long id) {
        return null;
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(Long id) {

    }
}
