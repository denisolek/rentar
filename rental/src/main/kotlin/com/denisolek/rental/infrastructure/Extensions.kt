package com.denisolek.rental.infrastructure

import com.denisolek.rental.cars.model.event.CarEvent
import com.denisolek.rental.customers.model.event.CustomerEvent
import com.denisolek.rental.infrastructure.gson.Gson
import com.denisolek.rental.rentals.model.event.RentalEvent
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.concurrent.Future

fun Producer<String, String>.send(event: Event): Future<RecordMetadata> {
    val result = when (event) {
        is RentalEvent -> this.send(ProducerRecord(Globals.RENTALS_TOPIC, "${event.aggregateId}", event.toJson()))
        else -> throw Exception(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event type")
    }
    this.flush()
    return result
}

fun <T, ID> JpaRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)

fun String.toRentalEvent(): RentalEvent = Gson.create().fromJson(this, RentalEvent::class.java)

fun String.toCarEvent(): CarEvent = Gson.create().fromJson(this, CarEvent::class.java)

fun String.toCustomerEvent(): CustomerEvent = Gson.create().fromJson(this, CustomerEvent::class.java)

fun LocalDateTime.isBeforeOrEqual(localDateTime: LocalDateTime) = !this.isAfter(localDateTime)

fun LocalDateTime.isAfterOrEqual(localDateTime: LocalDateTime) = !this.isBefore(localDateTime)

fun LocalDateTime.isBetweenInclusive(startTime: LocalDateTime, endTime: LocalDateTime) =
    this.isAfterOrEqual(startTime) && this.isBeforeOrEqual(endTime)

fun LocalDateTime.isBetweenInclusiveStart(startTime: LocalDateTime, endTime: LocalDateTime) =
    this.isAfterOrEqual(startTime) && this.isBefore(endTime)

fun LocalDateTime.isBetweenInclusiveEnd(startTime: LocalDateTime, endTime: LocalDateTime) =
    this.isAfter(startTime) && this.isBeforeOrEqual(endTime)