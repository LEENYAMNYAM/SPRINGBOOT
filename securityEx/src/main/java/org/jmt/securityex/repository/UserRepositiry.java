package org.jmt.securityex.repository;

import org.jmt.securityex.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositiry extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
