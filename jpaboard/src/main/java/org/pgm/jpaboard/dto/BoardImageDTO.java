package org.pgm.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardImageDTO {
    private int uuid;
    private int filename;
    private int ord;
}
