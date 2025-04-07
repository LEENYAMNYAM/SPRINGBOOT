package org.jmt.jpa01.controller;

import org.jmt.jpa01.domain.Board;
import org.jmt.jpa01.dto.BoardDTO;
import org.jmt.jpa01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/list")
    public void list(Model model) {
        List<BoardDTO> boardList = boardService.readAllBoards();
        model.addAttribute("boardList", boardList);
    }

//  register폼
    @GetMapping("/register")
    public void register() {
    }
//  register
    @PostMapping("/register")
    public String register(BoardDTO boardDTO) {
        boardService.registerBoard(boardDTO);
        /* redirect는 html이 아니라, 다시 컨트롤러 함수를 찾아가게 해줌*/
        return "redirect:/board/list";
    }
// read & modify 폼
    @GetMapping({"/read", "/modify"})
    public void read_modify(@RequestParam("bno") Long bno, Model model) {
        BoardDTO boardDTO = boardService.readBoard(bno);
        model.addAttribute("board", boardDTO);
    }

//  modify
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO) {
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?bno="+boardDTO.getBno();
    }

//  delete
    @GetMapping("/remove")
    public String remove(@RequestParam("bno") Long bno) {
        boardService.deleteBoard(bno);
        return "redirect:/board/list";
    }

}
