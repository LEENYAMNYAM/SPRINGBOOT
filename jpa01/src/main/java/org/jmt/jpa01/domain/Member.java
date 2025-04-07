package org.jmt.jpa01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbl_member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     @Id 해주면 id를 primarykey로 만들어줌
     생성전략 : IDENTITY는 primarykey를 AUTOINCREAMENT */
    private Long id;
    /* @와 연동되는 필드값은 꼭 객체형이여야 함.( int X -> INTEGER O ) */
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique=true)
    private String email;
    @Column(name="address")
    private String addr;

}
