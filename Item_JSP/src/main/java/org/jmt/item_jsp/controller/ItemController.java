package org.jmt.item_jsp.controller;

import lombok.extern.log4j.Log4j2;
import org.jmt.item_jsp.dto.ItemDTO;
import org.jmt.item_jsp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@Log4j2
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(ItemDTO itemDTO) {
        itemService.registerItem(itemDTO);
        log.info(itemDTO);
        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        List<ItemDTO> itemList = itemService.readAllItem();
        model.addAttribute("itemList", itemList);
    }

    @GetMapping({"/read", "/modify"})
    public void modify(Long id, Model model) {
        ItemDTO item = itemService.readItem(id);
        model.addAttribute("item", item);
    }

    @PostMapping("/modify")
    public String modify(ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
        return "redirect:/item/read?id="+itemDTO.getId();
    }

    @GetMapping("/remove")
    public String remove(Long id) {
        itemService.deleteItem(id);
        return "redirect:/item/list";
    }
}
