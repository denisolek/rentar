package com.denisolek.management.customers

import com.denisolek.management.infrastructure.Globals
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ConsumerListener {

    @KafkaListener(topics = [Globals.CONSUMERS_TOPIC])
    fun process(message: String) {
        println(message)
    }
}