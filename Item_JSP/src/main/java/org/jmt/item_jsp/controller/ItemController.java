package org.jmt.item_jsp.controller;

import lombok.extern.log4j.Log4j2;
import org.jmt.item_jsp.ItemDTO;
import org.jmt.item_jsp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
