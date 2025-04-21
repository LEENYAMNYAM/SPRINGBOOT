package org.jmt.securityex.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name="t_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String title;
    private String writer;
    private String content;
    @CreationTimestamp
    @Column(name="regdate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;
    private Long hitcount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /* @PrePersist : Entity가 저장되기 전에 먼저 호출해라 */
    @PrePersist
    public void prePersist() {
        this.hitcount = this.hitcount == null ? 0 : this.hitcount;
    }

    public void updateHitCount() {
        this.hitcount+=1;
    }
}
