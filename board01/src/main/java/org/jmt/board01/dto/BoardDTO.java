package org.jmt.board01.dto;

import lombok.*;

import java.util.Date;

// Data : getter, setter, toString을 가지도록 해줌
// 나머지 어노테이션도 각각의 생성자를 만들어 줌
// NoArgs : 파라미터 없는 기본생성자
// AllArgs : 모든 필드값을 파라미터로 받는 생성자
// RequiredArgs : 일부분만 파라미터로 받는 생성자 final을 필요로함
// Builder 등 : 여러가지 생성자를 만들 수 있음.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int bno;
    private String title;
    private String content;
    private String author;
    private Date postdate;
    private int readcount;

}
