package org.jmt.jpaboard.service;

import org.jmt.jpaboard.domain.ReplyEntity;
import org.jmt.jpaboard.dto.PageRequestDTO;
import org.jmt.jpaboard.dto.PageResponseDTO;
import org.jmt.jpaboard.dto.ReplyDTO;

public interface ReplyService {
    void register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

    default ReplyEntity dtoToEntity(ReplyDTO replyDTO) {
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rno( replyDTO.getRno() )
                .replyText( replyDTO.getReplyText() )
                .author( replyDTO.getAuthor() )
                .build();
        return replyEntity;
    }

    default ReplyDTO entityToDto(ReplyEntity replyEntity) {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno( replyEntity.getRno() )
                .replyText( replyEntity.getReplyText() )
                .author( replyEntity.getAuthor() )
                .regDate( replyEntity.getRegDate() )
                .updateDate( replyEntity.getUpdateDate() )
                .build();
        return replyDTO;
    }
}
