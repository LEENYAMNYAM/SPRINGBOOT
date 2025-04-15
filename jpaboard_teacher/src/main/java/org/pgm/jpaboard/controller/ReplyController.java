package org.pgm.jpaboard.controller;

import lombok.extern.log4j.Log4j2;
import org.pgm.jpaboard.dto.PageRequestDTO;
import org.pgm.jpaboard.dto.PageResponseDTO;
import org.pgm.jpaboard.dto.ReplyDTO;
import org.pgm.jpaboard.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }
    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        Map<String,Long> map = new HashMap<>();
        Long rno=replyService.register(replyDTO);
        map.put("rno",rno);
        return map;
    }
    @GetMapping("/{rno}")
    public ReplyDTO read(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }
    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getReplies(
            @PathVariable("bno") Long bno, PageRequestDTO pageRequest) {
        PageResponseDTO<ReplyDTO> responseDTO=
                replyService.getListOfBoard(bno,pageRequest);
        return responseDTO;
    }
    @DeleteMapping("/{rno}")
    public Map<String,Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String,Long> map = new HashMap<>();
        map.put("rno",rno);
        return map;
    }
    @PutMapping(value = "/{rno}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(
            @PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String,Long> map = new HashMap<>();
        map.put("rno",rno);
        return map;
    }
}
