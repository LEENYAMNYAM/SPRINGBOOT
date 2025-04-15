package org.jmt.jpaboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardImage implements Comparable<BoardImage>{
    @Id
    private String uuid;
    private String filename;
    private int ord;
    @ManyToOne
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;

    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }
    public void changeBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }
}
