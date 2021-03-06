package com.denisolek.management.customers.infrastructure.config

import com.denisolek.management.infrastructure.application.Application
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.KeyValueStore
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
class StreamConfig(val application: Application) {
    @Bean
    fun topology(): Topology {
        val builder = StreamsBuilder()
        val allEvents = builder.table(
            "events", Materialized.`as`<String, String, KeyValueStore<Bytes, ByteArray>>("allEvents")
                .withKeySerde(Serdes.String())
                .withValueSerde(Serdes.String())
        )
        return builder.build()
    }

    @Bean
    fun kafkaStreams(): KafkaStreams {
        val props = Properties()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = application.name
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = application.kafkaBootstrapServers
        props[StreamsConfig.COMMIT_INTERVAL_MS_CONFIG] = 100
        props[ConsumerConfig.GROUP_ID_CONFIG] = UUID.randomUUID().toString()
        return KafkaStreams(topology(), props)
    }

    @Configuration
    class Setup(val kafkaStreams: KafkaStreams, val eventProducer: Producer<String, String>) {
        private val logger = LoggerFactory.getLogger(javaClass)

        @PostConstruct
        fun startStreams() {
            kafkaStreams.start()
        }

        @PreDestroy
        fun closeStreams() {
            eventProducer.close()
            kafkaStreams.close()
        }
    }
}