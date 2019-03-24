package com.lfm.ers

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
//@EnableScheduling
@MapperScan("com.lfm.ers.mapper")
@EnableTransactionManagement
open class Application{
    companion object {
        @JvmStatic
        fun main(args:Array<String>){
            SpringApplication.run(Application::class.java,*args)
        }
    }
}


