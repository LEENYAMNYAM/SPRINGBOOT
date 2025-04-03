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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     @Id 해주면 id를 primarykey로 만들어줌
     생성전략 : IDENTITY는 primarykey를 AUTOINCREAMENT */
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique=true)
    private String email;
    @Column(name="address")
    private String addr;

}
