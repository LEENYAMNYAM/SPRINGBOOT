package org.jmt.jpaboard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_reply")
public class ReplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    /*  */
    @JoinColumn(name="bno")
    private BoardEntity boardEntity;
    @Column(nullable = false, length=1000)
    private String replyText;
    @Column(nullable = false, length=100)
    private String author;



}
