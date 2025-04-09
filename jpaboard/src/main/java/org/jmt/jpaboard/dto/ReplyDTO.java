package org.jmt.jpaboard.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jmt.jpaboard.domain.BaseEntity;
import org.jmt.jpaboard.domain.ReplyEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    @NotEmpty
    private Long rno;
    @NotEmpty
    private String replyText;
    @NotEmpty
    private String author;
    private Long bno;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
