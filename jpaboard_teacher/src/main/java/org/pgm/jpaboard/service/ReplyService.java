package org.pgm.jpaboard.service;

import org.pgm.jpaboard.domain.ReplyEntity;
import org.pgm.jpaboard.dto.PageRequestDTO;
import org.pgm.jpaboard.dto.PageResponseDTO;
import org.pgm.jpaboard.dto.ReplyDTO;
import org.springframework.data.domain.PageRequest;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

    default ReplyEntity dtoToEntity(ReplyDTO replyDTO) {
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rno(replyDTO.getRno())
                .replyText(replyDTO.getReplyText())
                .author(replyDTO.getAuthor())
                .build();
        return replyEntity;
    }
    default ReplyDTO entityToDto(ReplyEntity replyEntity) {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(replyEntity.getRno())
                .replyText(replyEntity.getReplyText())
                .author(replyEntity.getAuthor())
                .regDate(replyEntity.getRegDate())
                .updateDate(replyEntity.getUpdateDate())
                .bno(replyEntity.getBoardEntity().getBno())
                .build();
        return replyDTO;
    }
}
