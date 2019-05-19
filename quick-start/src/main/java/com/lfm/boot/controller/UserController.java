package com.lfm.boot.controller;

import com.lfm.boot.entity.SysUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/list")
    @HystrixCommand(fallbackMethod = "defaultList")
    public List<SysUser> list(){
        int length = 5;
        List<SysUser> list = new ArrayList<>(5);
        for (int i =0;i < length;i++){
            SysUser sysUser = new SysUser("user" + i, "password" + i,port);
            list.add(sysUser);
        }
        return list;
    }


    public List<SysUser> defaultList(){
        return Collections.emptyList();
    }
}
