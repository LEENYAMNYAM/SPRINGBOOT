package org.jmt.jpa01.repository;

import org.jmt.jpa01.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
