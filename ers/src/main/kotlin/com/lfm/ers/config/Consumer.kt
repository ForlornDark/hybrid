package com.lfm.ers.config

import org.springframework.stereotype.Component
import org.springframework.kafka.annotation.KafkaListener



@Component
class Consumer {
    @KafkaListener(topics = arrayOf("test"))
    fun processMessage(message: SampleMessage) {
        println("Received sample message [$message]")
    }
}