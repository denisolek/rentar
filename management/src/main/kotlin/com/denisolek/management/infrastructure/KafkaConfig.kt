package com.denisolek.management.infrastructure

import com.denisolek.management.infrastructure.application.Application
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class KafkaConfig(val application: Application) {
    @Bean
    fun eventProducer(): Producer<String, String> {
        val props = Properties()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = application.name
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = application.kafkaBootstrapServers
        props[ProducerConfig.CLIENT_ID_CONFIG] = "ManagementService"
        return KafkaProducer(props, StringSerializer(), StringSerializer())
    }
}

