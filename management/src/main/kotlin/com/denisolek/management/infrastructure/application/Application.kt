package com.denisolek.management.infrastructure.application

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Application(
    val name: String,
    val kafkaBootstrapServers: String
)

@Configuration
class ApplicationConfig {
    @Value("\${spring.application.name}")
    val applicationName: String = "errorapplicationName"

    @Value("\${spring.kafka.bootstrap-servers}")
    val kafkaBootstrapServers = "errorkafkaBootstrapServers"

    @Bean
    fun setupApplication() = Application(
        name = applicationName,
        kafkaBootstrapServers = kafkaBootstrapServers
    )
}