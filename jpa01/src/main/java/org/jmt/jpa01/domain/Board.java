package org.jmt.jpa01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 3000)
    private String content;
    @Column(nullable = false, length = 50)
    private String author;
    private LocalDateTime postdate;
    private int readcount;

    public void updateReadcount() {
        readcount = readcount + 1;
    }
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
