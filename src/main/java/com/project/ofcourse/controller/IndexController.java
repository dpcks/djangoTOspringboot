package com.project.ofcourse.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class IndexController {

    @GetMapping("/")
    public String index() {
        log.info("index 메서드 call.....");
        return "index";
    }
}
