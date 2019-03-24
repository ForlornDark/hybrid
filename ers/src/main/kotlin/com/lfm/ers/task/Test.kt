package com.lfm.ers.task

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class Test {
    @Scheduled(cron = "0/30 * * * * ?")
    fun execute(){
        System.out.println(LocalDateTime.now())
    }
}