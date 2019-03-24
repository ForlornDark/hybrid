package com.lfm.ers.config

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component





@Component
class Producer {
    private var kafkaTemplate: KafkaTemplate<Any, SampleMessage> ?= null

    constructor(kafkaTemplate: KafkaTemplate<Any, SampleMessage>) {
        this.kafkaTemplate = kafkaTemplate
    }



    fun send(message: SampleMessage) {
        kafkaTemplate?.send("test", message)
        println("Sent sample message [$message]")
    }
}