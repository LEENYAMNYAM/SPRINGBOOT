package org.jmt.jpaboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL orphanRemoval=true)
    @builder.Default
    @BatchSize(size=20)
    /* imageSet 생성할때 같이 생성하라는 의미 */
    private Set<BoardImage> imageSet=new HashSet<>();

    public void addImage(String uuid, String filename) {
        BoardImage image = BoardImage.builder()
                .uuid(uuid)
                .filename(filename)
                .boardEntity(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(image);
    }

    public void removeImage() {
        imageSet.forEach(BoardImage boardImage -> boardImage.changeBoardEntity(null));
        /*
        String[] strs ={"aaa","bbb"};
        for(String k:strs){} */
        this.imageSet.clear();

    }


    public void updateReadcount() {
        readcount = readcount + 1;
    }
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
