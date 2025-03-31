package org.jmt.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("hello")
    public String getHello() {
        return "hello";
    }
}
