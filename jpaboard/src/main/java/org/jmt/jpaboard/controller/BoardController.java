package org.jmt.jpaboard.controller;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void listBoards(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(BoardDTO boardDTO) {
        boardService.registerBoard(boardDTO);
        return "redirect:/board/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read_modify(PageRequestDTO pageRequestDTO, Long bno, Model model) {
        BoardDTO boardDTO = boardService.readBoard(bno);
        model.addAttribute("board", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, Model model) {
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?bno=" + boardDTO.getBno();
    }

    @GetMapping("/remove")
    public String remove(Long bno) {
        boardService.deleteBoard(bno);
        return "redirect:/board/list";
    }
}
