package org.jmt.board01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.board01.dto.BoardDTO;
import org.jmt.board01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @GetMapping("/list")
    public String getList(Model model) {
        List<BoardDTO> boardlist = boardService.getList();
        model.addAttribute("boardlist", boardlist);
        return "board/boardList";
    }

    @GetMapping ("/insert")
    public String insert(BoardDTO boardDTO) {
        return "board/boardInsert";
    }

    @PostMapping("/insert")
    public String postInsert(BoardDTO boardDTO) {
        boardService.insertBoard(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/getBoard")
    public String getBoard(@RequestParam("bno") int bno, Model model) {
        /* 지금처럼 파라미터이름과 RequsetParam 으로 받아오는 값의 명칭이 같으면  RequestParam 안적어줘도 됨. */
        BoardDTO boardDTO = boardService.getBoard(bno);
        model.addAttribute("board", boardDTO);
        return "board/boardView";
    }
}
