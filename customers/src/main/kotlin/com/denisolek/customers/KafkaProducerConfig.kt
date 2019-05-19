package com.denisolek.customers

import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

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