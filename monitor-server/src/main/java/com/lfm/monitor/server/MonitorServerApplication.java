package com.lfm.monitor.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MonitorServerApplication {

    public static void main(String[] args){
        SpringApplication.run(MonitorServerApplication.class, args);
    }
}
