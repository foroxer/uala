package com.juancastelli.uala.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    private final String PONG = "pong";
    @GetMapping("/ping")
    public String ping(){
        return PONG;
    }
}
