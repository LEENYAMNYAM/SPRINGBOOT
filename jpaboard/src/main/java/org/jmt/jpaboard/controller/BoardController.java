package org.jmt.jpaboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.dto.upload.UploadFileDTO;
import org.jmt.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
    @Value("${org.jmt.jpaboard.upload.path}")
    private String uploadPath;

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
    public String register(UploadFileDTO uploadFileDTO, BoardDTO boardDTO) {
        List<String> strFileNames = null;
        if(uploadFileDTO.getFiles()!=null && !uploadFileDTO.getFiles().get(0).getOriginalFilename().equals("")){
            /* 수정된 파일 업로드 하기 */
            strFileNames = fileUpload(uploadFileDTO);
        }
        boardDTO.setFileNames(strFileNames);
        boardService.registerBoard(boardDTO);
        return "redirect:/board/list";
    }


    @GetMapping({"/read", "/modify"})
    public void read_modify(PageRequestDTO pageRequestDTO, Long bno, Model model) {
        BoardDTO boardDTO = boardService.readBoard(bno);
        model.addAttribute("board", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(UploadFileDTO uploadFileDTO, BoardDTO boardDTO, Model model) {
        List<String> strFileNames = null;
        if(uploadFileDTO.getFiles()!=null && !uploadFileDTO.getFiles().get(0).getOriginalFilename().equals("")){

            BoardDTO boardDTO1 = boardService.readBoard(boardDTO.getBno());
            /* 기존파일이 존재한다면 삭제하기 */
            List<String> fileNames = boardDTO1.getFileNames();
            if(fileNames != null && fileNames.size() > 0){
                removeFile(fileNames);
            }
            strFileNames = fileUpload(uploadFileDTO);
            boardDTO.setFileNames(strFileNames);
        }
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?bno=" + boardDTO.getBno();
    }

    @GetMapping("/remove")
    public String remove(Long bno) {
        BoardDTO boardDTO = boardService.readBoard(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0){
            removeFile(fileNames);
        }
        boardService.deleteBoard(bno);
        return "redirect:/board/list";
    }

    /* 이미지 링크 만들기 */
    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFileGet
    (@PathVariable("filename") String filename){
        Resource resource = new FileSystemResource(uploadPath + File.separator + filename);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try{
            headers.add("Content-Type",
                    Files.probeContentType(resource.getFile().toPath()));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    private List<String> fileUpload(UploadFileDTO uploadFileDTO) {
        List<String> list = new ArrayList<>();

        if(uploadFileDTO.getFiles() != null){
            uploadFileDTO.getFiles().forEach(multiFile -> {
                String originalFilename = multiFile.getOriginalFilename();
                log.info("~~~~~~~~~~~~~~~~~~" + originalFilename);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid +"_" + originalFilename);

                log.info(originalFilename);
                boolean image=false;

                try{
                    /* 업로드 Path에 이미지 저장 */
                    multiFile.transferTo(savePath);
                    if(Files.probeContentType(savePath).startsWith("image")){
                        image=true;
                        File thumbFile = new File(uploadPath,"s_" + uuid + "_" + originalFilename);

                        /* 썸네일 파일을 만들어줌(
                        savePath.toFile() : 진짜 파일
                        thumbFile : 썸네일파일
                        200*200 : 파일크기 */
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                list.add(uuid + "_" + originalFilename);
            });
        }
        return list;
    }

    private void removeFile(List<String> fileNames) {
        for (String fileName : fileNames) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            Map<String, Boolean> resultMap = new HashMap<>();
            boolean removed = false;
            try{
                /* 파일의 컨텐츠 타입을 얻음(image인지 아닌지 파악하려고) */
                String contentType = Files.probeContentType(resource.getFile().toPath());

                log.info(resource.getFile().toPath());

                removed = resource.getFile().delete();
                /* 파일(이미지일때는 원본파일만) 삭제되고 삭제 성공시 True가 리턴됨*/

                /* image 파일일 경우는 썸네일파일말고 원본파일도 삭제해야함. */
                if(contentType.startsWith("image")){
                    String fileName1 = "s_" + fileName;
                    File thumnailFile = new File(uploadPath + File.separator + fileName1);
                    thumnailFile.delete();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
