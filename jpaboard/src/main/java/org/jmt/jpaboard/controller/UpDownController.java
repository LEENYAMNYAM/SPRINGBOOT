package org.jmt.jpaboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.jmt.jpaboard.dto.BoardDTO;
import org.jmt.jpaboard.dto.upload.UploadFileDTO;
import org.jmt.jpaboard.dto.upload.UploadResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@Log4j2
@RequestMapping("/upload")
public class UpDownController {
    @Value("${org.jmt.jpaboard.upload.path}")
    private String uploadPath;

    @GetMapping("/uploadForm")
    public void uploadForm() {}

    @PostMapping(value = "/uploadPro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(UploadFileDTO uploadFileDTO, BoardDTO boardDTO, Model model) {
        /* form값이 넘어올때 name속성값과 필드값이 같으면 매핑을 자동으로 시켜줌 */
        log.info("~~~~~~~~~~~~~~~~~~" + uploadFileDTO);

        List<UploadResultDTO> list = new ArrayList<>();

        if(uploadFileDTO.getFiles() != null){
            uploadFileDTO.getFiles().forEach(multiFile -> {
                String originalFilename = multiFile.getOriginalFilename();
                log.info("~~~~~~~~~~~~~~~~~~" + originalFilename);
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid +"_" + originalFilename);
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
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .filename(originalFilename)
                        .image(image)
                        .build());
            });
        }
        model.addAttribute("fileList", list);
        model.addAttribute("boardDTO", boardDTO);
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
    @GetMapping("/remove")
    public String removeFile(@RequestParam("filename") String filename){
        Resource resource = new FileSystemResource(uploadPath + File.separator + filename);
        String resourceName = resource.getFilename();
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;
        try{
            String contentType = Files.probeContentType(resource.getFile().toPath());

            removed = resource.getFile().delete();
            /* 파일이 삭제(이미지일때는 썸네일만) 되고 삭제 성공시 True가 리턴됨*/

            /* image 파일일 경우는 썸네일파일말고 원본파일도 삭제해야함. */
            if(contentType.startsWith("image")){
                String fileName1 = filename.replace("s_", "");
                File originalFile = new File(uploadPath + File.separator + fileName1);
                originalFile.delete();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        resultMap.put("result", removed);

        return "/upload/uploadForm";
    }


}
