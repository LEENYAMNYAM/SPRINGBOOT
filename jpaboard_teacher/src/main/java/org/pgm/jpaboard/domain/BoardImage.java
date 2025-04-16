package org.pgm.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardImage implements Comparable<BoardImage>{
    @Id
    private String uuid;
    private String filename;
    private int ord;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bno")
    private BoardEntity boardEntity;

    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }
    public void changeBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

}
