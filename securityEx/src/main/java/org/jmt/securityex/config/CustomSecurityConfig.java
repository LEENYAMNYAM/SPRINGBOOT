package org.jmt.securityex.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
@EnableWebSecurity
 : 시큐리티 쓰겠다. 웹시큐리티를 활성화하는 클래스라는것을 의미해줌
@RequiredArgsConstructor
 : 자동으로 빈객체로 만들도록 해줌
 */
@Configuration
@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                /* 웹시큐리티 설정중에서 무시(시큐리티를 적용되지 않아야되는 부분)받아야되는 부분을 작성 */

                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        /* 스태틱 로케이션(resources/static 폴더안에 있는 내용)의 모든 요소들은 security의 제한을 받지않도록 해줌.(security에서 제외시킴)
         *  로그인하면 적용되지 않아야하는 것들(프론트부분이라거나) 처리 함  */
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
        /* 시큐리티는 콘트롤러 패싱하기전에 무조건 먼지 이루어짐 */
        /* 필터체인을 씌우면 컨트롤러보다 먼저 실행?됨 인증사용사별 권한 부여 가능 */
        log.info("securityFilterChain");

        return http
                .csrf(httpSecurityCsrfConf -> httpSecurityCsrfConf.disable())
                /* POST로 보내는 데이터는 중요한 친구라서 아무말없으면 csrf토큰이라고 명시해서 보내줘야함. 안적으면 디폴트값이 csrf위조방지를 enalble이 됨.
                * disable 해야지 restController를 못씀. */
                .authorizeHttpRequests(authorizeRequestsConfig ->
                        authorizeRequestsConfig
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/login", "/signup", "/user/**", "/", "/all").permitAll()
                                /* /login, /sinup, /user/**, /, /all로 접근하는 모든것은 누구나 접근가능 */
                                .requestMatchers(HttpMethod.GET,"/board/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                                /* /admin/** 로 접근하는 모든 것은 ADMIN만 접근가능 */
                                .anyRequest().authenticated())
                                /* 그 외에 접근들(anyRequest)은 인증된(로그인 된) 사람만 접근가능 */
                .formLogin(formLoginConfig -> formLoginConfig
                        /* 람다 함수 변수 파라미터는 */
                        .loginPage("/user/login")
                        /* 로그인 처음할때 /user/login(호출 url)을 쓰면 로그인할 수 있게 해 줌 */
                        .loginProcessingUrl("/loginProcess")
                        /* 로그인 처리하려면 /loginProcess 라고 쓰면 알아서 함.(별칭을 달아줌) */
                        .usernameParameter("username")
                        /* username의 파라미터의 이름 정하기 지금은 "username"이라고 정했음. */
                        .passwordParameter("password")
                        /* passwoer 파라미터의 이름정하기 */
                        /* 로그인 처리할 때 username과 password로 처리하겠다. username대신 email 같은거 사용가능 */
                        .defaultSuccessUrl("/")
                        /* 로그인 성공시 가야할 url */
                        .permitAll())
                        /* 로그인 폼은 누구나 접속 가능하도록 권한 설정함. */

                .logout(logoutConfig -> logoutConfig
                        .logoutUrl("/logout")
                        /* 로그아웃 호출 url */
                        .logoutSuccessUrl("/")
                        /* 로그아웃 성공시 갈 url */
                        .invalidateHttpSession(true)
                        /* 세션(로그인후 로그인 되어 있는 상태를 저장해둔 곳)을 무효화 시킴 */
                        .clearAuthentication(true))
                        /* 인증 정보 지우기 */
                .build();
                /* 지금까지 작성한 내용을 함수로 만들어줌*/
    }

    /* 암호화 객체 */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /* BCryptPasswordEncoder() : 패스워드를 암호화하는 방법중 하나
        * 특징으로 인코딩을 통해 암호화는 되는데, 복호화가 불가능함.
        * 때문에 로그인시 암호를 올바르게 작성했는가는 암호화가 똑같이 되었는가를 비교하게 됨. */

    }


}
