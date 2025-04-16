package org.jmt.securityex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jmt.securityex.domain.User;
import org.jmt.securityex.repository.UserRepositiry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepositiry userRepositiry;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public void join(){
    }
    @PostMapping("/register")
    public String register(User user){
        String Password = passwordEncoder.encode(user.getPassword());
        user.setPassword(Password);
        user.setRole("USER");
        userRepositiry.save(user);
        return "redirect:/user/login";
    }
    @GetMapping("/login")
    public void login(){

    }

}
