package org.jmt.jpa01.repository;

import org.jmt.jpa01.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /* primary key로의 검색은 자주 쓰기 때문에 안적어도 됨. JpaRepository에서 알아서 해결해줌 */
//    Optional<Member> findById(Long id );

    @Query("select m from Member as m where m.name=:name")
    /* table명은 ENTITY(클래스)명으로 적어줘야함 */
    Member name(String name);
    Member findByEmail(String email);
//    Member findByUsername(String name);

}
