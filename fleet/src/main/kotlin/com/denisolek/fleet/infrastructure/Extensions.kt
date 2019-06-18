package com.denisolek.fleet.infrastructure

import com.denisolek.fleet.cars.model.event.CarEvent
import com.denisolek.fleet.infrastructure.gson.Gson
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import java.util.concurrent.Future

fun Producer<String, String>.send(event: Event): Future<RecordMetadata> {
    val result = when (event) {
        is CarEvent -> this.send(ProducerRecord(Globals.CARS_TOPIC, "${event.aggregateId}", event.toJson()))
        else -> throw Exception(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event type")
    }
    this.flush()
    return result
}

fun <T, ID> JpaRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)

fun String.toCarEvent(): CarEvent = Gson.create().fromJson(this, CarEvent::class.java)