package com.denisolek.rental.cars.facade

import com.denisolek.rental.cars.infrastructure.CarRepository
import com.denisolek.rental.cars.model.CarFactory
import com.denisolek.rental.cars.model.event.CarAdded
import com.denisolek.rental.cars.model.event.CarAddingCancelled
import com.denisolek.rental.cars.model.event.CarUpdated
import com.denisolek.rental.cars.model.event.CarUpdatingCancelled
import com.denisolek.rental.infrastructure.Globals
import com.denisolek.rental.infrastructure.toCarEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CarEventHandler(
    val repository: CarRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [Globals.CARS_TOPIC])
    fun process(message: String) {
        val event = message.toCarEvent()
        when (event) {
            is CarAdded -> handle(event)
            is CarUpdated -> handle(event)
            is CarAddingCancelled -> handle(event)
            is CarUpdatingCancelled -> handle(event)
        }
    }

    fun handle(e: CarAdded) {
        CarFactory.create(e).run {
            repository.save(this)
            logger.info("[CarAdded] {${this.id.value}} - Handled")
        }
    }

    fun handle(e: CarUpdated) {
        val car = repository.findByIdOrThrow(e.aggregateId)
        car.apply(e)
        repository.save(car)
        logger.info("[CarUpdated] {${e.aggregateId}} - Handled")
    }

    fun handle(e: CarAddingCancelled) {
        repository.removeIfExists(e.aggregateId)
        logger.info("[CarAddingCancelled] {${e.aggregateId}} - Handled")
    }

    fun handle(e: CarUpdatingCancelled) {
        val car = repository.findByIdOrThrow(e.aggregateId)
        car.apply(e)
        repository.save(car)
        logger.info("[CarUpdatingCancelled] {${e.aggregateId}} - Handled")
    }
}