package com.lfm.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuickStartApplication {
    public static void main(String[] args){
        SpringApplication.run(QuickStartApplication.class,args);
    }
}
