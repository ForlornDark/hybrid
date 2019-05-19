package com.lfm.api.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeoutController {
    @GetMapping("/timeout")
    public String timeout() throws InterruptedException {
        Thread.sleep(80000);
        return "timeout";
    }
}
