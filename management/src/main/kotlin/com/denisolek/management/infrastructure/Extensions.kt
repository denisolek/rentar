package com.denisolek.management.infrastructure

import com.denisolek.management.customers.domain.event.CustomerEvent
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.http.HttpStatus
import java.util.concurrent.Future

fun Producer<String, String>.send(event: Event): Future<RecordMetadata> {
    val result = when (event) {
        is CustomerEvent -> this.send(ProducerRecord(Globals.CONSUMERS_TOPIC, "${event.aggregateId}", event.toJson()))
        else -> throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event type")
    }
    this.flush()
    return result
}