package org.pgm.jpaboard.controller;

import groovy.util.logging.Log;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.pgm.jpaboard.dto.BoardDTO;
import org.pgm.jpaboard.dto.PageRequestDTO;
import org.pgm.jpaboard.dto.PageResponseDTO;
import org.pgm.jpaboard.dto.upload.UploadFileDTO;
import org.pgm.jpaboard.dto.upload.UploadResultDTO;
import org.pgm.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
    @Value("${org.pgm.jpaboard.upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void listBoard(PageRequestDTO pageRequestDTO,Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequest", pageRequestDTO);
    }

    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(UploadFileDTO uploadFileDTO,BoardDTO boardDTO){
        List<String> strFileNames=null;
        if(uploadFileDTO.getFiles() != null &&
                !uploadFileDTO.getFiles().get(0).getOriginalFilename().equals("")){
            strFileNames = fileUpload(uploadFileDTO);
        }

        boardDTO.setFileNames(strFileNames);
        boardService.registerBoard(boardDTO);
        return "redirect:/board/list";
    }
    @GetMapping({"/read","/modify"})
    public void read_modify(PageRequestDTO pageRequestDTO, Long bno, Model model){
        BoardDTO boardDTO = boardService.readBoard(bno);
        model.addAttribute("board", boardDTO);
    }
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, Model model){
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?bno="+boardDTO.getBno();
    }
    @GetMapping("/remove")
    public String remove(Long bno){
        boardService.deleteBoard(bno);
        return "redirect:/board/list";
    }


    private List<String> fileUpload(UploadFileDTO uploadFileDTO){
        List<String> list = new ArrayList<>();
        if(uploadFileDTO.getFiles() !=null){
            uploadFileDTO.getFiles().forEach(multiFile -> {
                String originalFilename = multiFile.getOriginalFilename();
                log.info("~~~~~~~~~~~~~~"+originalFilename);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath,uuid+"_"+originalFilename);
                boolean image=false;

                try{
                    multiFile.transferTo(savePath);
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image=true;
                        File thumbFile = new File(uploadPath,"s_"+uuid+"_"+originalFilename);
                        Thumbnailator.createThumbnail(savePath.toFile(),thumbFile,200,200);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                list.add(uuid+"_"+originalFilename);
            });
        }

        return list;
    }
}
