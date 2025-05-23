package org.pgm.jpaboard.service;

import lombok.RequiredArgsConstructor;
import org.pgm.jpaboard.domain.BoardEntity;
import org.pgm.jpaboard.domain.ReplyEntity;
import org.pgm.jpaboard.dto.PageRequestDTO;
import org.pgm.jpaboard.dto.PageResponseDTO;
import org.pgm.jpaboard.dto.ReplyDTO;
import org.pgm.jpaboard.repository.BoardRepository;
import org.pgm.jpaboard.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        ReplyEntity replyEntity =dtoToEntity(replyDTO);
        BoardEntity boardEntity=boardRepository
                .findById(replyDTO.getBno()).get();
        replyEntity.setBoardEntity(boardEntity);
        Long rno=replyRepository.save(replyEntity).getRno();
        return rno;
    }
    @Override
    public ReplyDTO read(Long rno) {
        ReplyEntity replyEntity=replyRepository.findById(rno).get();
        ReplyDTO replyDTO=entityToDto(replyEntity);
        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        ReplyEntity replyEntity = replyRepository.findById(replyDTO.getRno()).get();
        replyEntity.setReplyText(replyDTO.getReplyText());
        replyRepository.save(replyEntity);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("rno");
        Page<ReplyEntity> result=replyRepository.listOfBoard(bno, pageable);

        List<ReplyDTO> dtoList=result.getContent().stream()
                .map(replyEntity -> entityToDto(replyEntity))
                .collect(Collectors.toList());

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
