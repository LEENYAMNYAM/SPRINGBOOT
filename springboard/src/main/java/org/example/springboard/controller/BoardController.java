package org.example.springboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springboard.dto.BoardDTO;
import org.example.springboard.model.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    //추가폼
    @GetMapping("boardInsert")
    public String boardInsert() {
        log.info("boardInsert called");
        return "board/boardWrite";
    }

    //추가
    @PostMapping("boardInsert")
/*    public String boardPostInsert(@ModelAttribute BoardDTO boardDTO) {*/
    public String boardPostInsert(BoardDTO boardDTO) {
        log.info(boardDTO);
        boardService.insert(boardDTO);
        return "redirect:boardList";
    }

    //전체보기
    @GetMapping("boardList")
    public String boardList(@RequestParam(value = "searchField", defaultValue = "subject") String searchField,
                            @RequestParam(value = "searchWord", defaultValue = "") String searchWord,
                            Model model) {
        log.info("searchword    : " + searchWord);
    /* 검색없을때
        List<BoardDTO> boardList = boardService.boardList();
        int count = boardService.count();
    */
    //  검색기능 있을 때
        HashMap<String, String> map = new HashMap<>();
        map.put("searchField", searchField);
        map.put("searchWord", searchWord);
        List<BoardDTO> boardList = boardService.searchList(map);
        int count = boardService.searchCount(map);

        model.addAttribute("barr", boardList);
        model.addAttribute("count", count);
        return "board/boardList";
    }

    //상세보기
    @GetMapping("boardView")
    public String boardView(@RequestParam("num") int num, Model model) {
        BoardDTO board = boardService.boardGet(num);
        model.addAttribute("board", board);
        return "board/boardView";
    }

    //수정폼
    @GetMapping("boardUpdate")
    public String boardUpdate(@RequestParam("num") int num, Model model) {
        log.info("boardUpdate num :" + num);
        BoardDTO board = boardService.boardGet(num);
        model.addAttribute("board", board);
        return "board/boardUpdate";
    }

    //수정
    @PostMapping("boardUpdate")
    public String boardUpdate(@ModelAttribute BoardDTO boardDTO) {
        log.info(boardDTO);
        boardService.update(boardDTO);
        return "redirect:boardList";
    }

    //삭제
    @GetMapping("boardDelete")
    public String boardDelete(@RequestParam("num") int num) {
        log.info("boardDelete num :" + num);
        boardService.delete(num);
        return "redirect:boardList";
    }


}
