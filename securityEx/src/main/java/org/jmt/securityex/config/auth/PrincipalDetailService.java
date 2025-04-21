package org.jmt.securityex.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.securityex.domain.User;
import org.jmt.securityex.repository.UserRepositiry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepositiry userRepositiry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* 로그인된 유저 정보를 가져오는 Service */
        User user = userRepositiry.findByUsername(username);
        if(user == null) {
            return null;
        }
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        return principalDetails;
    }

}
