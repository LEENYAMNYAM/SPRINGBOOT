package org.jmt.jpaboard.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;

    @NotEmpty
    /* 비어있으면 안된다는 뜻 */
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String author;
    private int readcount;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
