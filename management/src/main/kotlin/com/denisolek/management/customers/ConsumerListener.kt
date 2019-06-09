package com.denisolek.management.customers

import com.denisolek.management.infrastructure.Globals
import com.denisolek.management.infrastructure.toCustomerEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ConsumerListener {
    @KafkaListener(topics = [Globals.CUSTOMERS_TOPIC])
    fun process(message: String) {
        val event = message.toCustomerEvent()
        println(event)
    }
}