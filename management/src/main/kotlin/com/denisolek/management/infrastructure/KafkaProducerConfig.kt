package com.denisolek.management.infrastructure

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaProducerConfig {
    @Value("\${kafka.host:localhost}")
    private val host: String? = null

    @Value("\${kafka.port:9092}")
    private val port: Int = 0

    companion object {
        const val topic = "events"
    }
}