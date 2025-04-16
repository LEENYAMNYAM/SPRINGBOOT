package org.jmt.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "boardEntity") // 중요!!
@EqualsAndHashCode(of = "uuid")    // 순환 방지!
@Entity
public class BoardImage implements Comparable<BoardImage>{
    @Id
    private String uuid;
    private String filename;
    private int ord;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;

    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }
    public void changeBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }
}
