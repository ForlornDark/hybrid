package com.lfm.ers.task

import com.lfm.ers.config.Producer
import com.lfm.ers.config.SampleMessage
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.quartz.QuartzJobBean
import java.time.LocalDateTime

class Test2 : QuartzJobBean() {

    @Autowired
    val producer: Producer?=null

    var name:String?= null

    val logger = LoggerFactory.getLogger(this.javaClass)
    override fun executeInternal(context: JobExecutionContext?) {
        System.out.println("sadafdsf:$name$this")
//        producer?.send(SampleMessage(1,"test,kafka"))
    }
}