package com.lfm.ers.config

import com.lfm.ers.service.SysUserService
import com.lfm.ers.task.Test2
import org.quartz.*
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler
import java.util.concurrent.Executor
import org.quartz.SimpleScheduleBuilder
import org.quartz.JobBuilder
import org.springframework.context.annotation.DependsOn


@Configuration
open class TaskConfig {
//    @Bean
//    open fun taskExecutor(): TaskScheduler {
//        return DefaultManagedTaskScheduler()
//    }
//    @Bean
//    open fun taskExecutor(): Executor {
//        return SimpleAsyncTaskExecutor();
//    }

    @Bean
    open fun test2Detail():JobDetail{
        val data = JobDataMap()
        data.put("name","caoni-------------------------------")
        return JobBuilder.newJob(Test2::class.java)
                .setJobData(data).withIdentity("test2")
                .storeDurably()
                .build()
    }

    @Bean
    open fun testTrigger(): Trigger {
        val builder = CronScheduleBuilder.cronSchedule("0/20 * * * * ? *")
//    val scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//            .withIntervalInSeconds(10)  //设置时间周期单位秒
//            .repeatForever()
    return TriggerBuilder.newTrigger().
                withIdentity("test2Trigger").
                forJob("test2").withSchedule(builder).build()
    }
}