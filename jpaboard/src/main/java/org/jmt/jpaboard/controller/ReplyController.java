package org.jmt.jpaboard.controller;

import lombok.extern.log4j.Log4j2;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.dto.ReplyDTO;
import org.jmt.jpaboard.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/home")
    public String home(){
        return "Hello World";
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    /* MediaType.APPLICATION_JSON_VALUE : 제이슨 형태로 보내겠다. */
    public Map<String, Long> register(@RequestBody ReplyDTO replyDTO) {
        /* @RequestBody : BODY속에 담겨있는 데이터는 객체형(제이슨형태)로 바꿔줌 그리고 replyDTO에 담음 */
        log.info("ReplyDTO: " + replyDTO);
        Map<String, Long> map = new HashMap<String, Long>();
        Long rno = replyService.register(replyDTO);
        map.put("rno", rno);
        return map;
    }

    @GetMapping("/{rno}")
    public ReplyDTO read(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getReplies(@PathVariable("bno") Long bno,
                                                PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return responseDTO;
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);
        return map;
    }

    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    /* 최근에는 consumes = MediaType.APPLICATION_JSON_VALUE 안써도 됨. */
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);
        return map;
    }


}
