package org.jmt.securityex.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        /* 스태틱 로케이션의 모든 요소들은 security의 제한을 받지않도록 security에서 제외시킴
        *  로그인하면 적용되지 않는 것들(프론트부분이라거나)이 발생하는 현상을 막아줌  */
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
            /* 필터체인을 씌우면 컨트롤러보다 먼저 실행?됨 인증사용사별 권한 부여 가능 */
        log.info("securityFilterChain");
        return http
                .csrf(httpSecurityCsrfConf -> httpSecurityCsrfConf.disable())
                /* POST로 보내는 데이터는 중요한 친구라서 아무말없으면 csrf토큰이라고 명시해서 보내줘야함. 안적으면 디폴트값이 csrf위조방지를 enalble이 됨. */
                .authorizeHttpRequests(authorizeRequestsConfig ->
                        authorizeRequestsConfig
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/login", "/signup", "/user/**", "/", "/all").permitAll()
                                /* /login, /sinup, /user/**, /, /all로 접근하는 모든것은 누구나 접근가능 */
                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                                /* /admin/** 로 접근하는 모든 것은 ADMIN만 접근가능 */
                                .anyRequest().authenticated())
                                /* 그 외에 접근들은 인증된(로그인 된) 사람만 접근가능 */
                .formLogin(formLoginConfig -> formLoginConfig
                        .loginPage("/user/login")
                        /* 로그인 호출 url */
                        .loginProcessingUrl("/loginProcess")
                        /* 로그인 처리는 /loginProcess 가 알아서 함.(별칭을 달아줌) */
                        .usernameParameter("username")
                        .passwordParameter("password")
                        /* 로그인 처리할 때 username과 password로 처리하겠다. username대신 email 같은거 사용가능 */
                        .defaultSuccessUrl("/")
                        /* 로그인 성공시 가야할 url */
                        .permitAll())
                .logout(logoutConfig -> logoutConfig
                        .logoutUrl("/logout")
                        /* 로그아웃 호출 url */
                        .logoutSuccessUrl("/")
                        /* 로그아웃 성공시 갈 url */
                        .invalidateHttpSession(true)
                        /* 세션을 무효화 시킴 */
                        .clearAuthentication(true))
                        /* 인증 정보 지우기 */
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
