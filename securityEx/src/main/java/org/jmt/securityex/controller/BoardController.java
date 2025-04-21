package org.jmt.securityex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.securityex.config.auth.PrincipalDetails;
import org.jmt.securityex.domain.Board;
import org.jmt.securityex.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(Board board, @AuthenticationPrincipal PrincipalDetails principal){
        boardService.insert(board, principal.getUser());
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("boardList", boardService.list());
    }
    @GetMapping({"/read", "/modify"})
    public void view(@RequestParam("num") Long num, Model model){
        model.addAttribute("board", boardService.findById(num));
    }

    @PostMapping("/modify")
    public String modify(Board board, @AuthenticationPrincipal PrincipalDetails principal,
                         RedirectAttributes redirectAttributes){
        boardService.update(board, principal.getUser());
        redirectAttributes.addAttribute("num", board.getNum());
        return "redirect:/board/read";

        /* redirectAttributes 안쓰면 이렇게 */
        //        return "redirect:/board/read?num=" + board.getNum();
    }

    @GetMapping("/remove")
    public String delete(@RequestParam("num") Long num){
        boardService.delete(num);
        return "redirect:/board/list";
    }
}
