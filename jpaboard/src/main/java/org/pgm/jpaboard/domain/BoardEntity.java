package org.pgm.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_board")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "imageSet")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false, length = 3000)
    private String content;
    @Column(nullable = false, length = 50)
    private String author;
    private int readcount;

    @OneToMany(mappedBy="boardEntity",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size=20)
    private Set<BoardImage> imageSet=new HashSet<>();

    public void addImage(String uuid, String filename){
        BoardImage image = BoardImage.builder()
                .uuid(uuid)
                .filename(filename)
                .boardEntity(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(image);
    }

    public void removeImage(){
        imageSet.forEach(boardImage ->
                boardImage.changeBoardEntity(null));
        this.imageSet.clear();

    }

//    @OneToMany
//    private List<ReplyEntity> replies;

    public void updateReadcount() {
        readcount = readcount + 1;
    }
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
