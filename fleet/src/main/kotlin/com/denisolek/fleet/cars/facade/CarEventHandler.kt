package com.denisolek.fleet.cars.facade

import com.denisolek.fleet.cars.facade.command.AddCarCancelledCommand
import com.denisolek.fleet.cars.facade.command.UpdateCarCancelledCommand
import com.denisolek.fleet.cars.facade.query.AddCarValidate
import com.denisolek.fleet.cars.facade.query.UpdateCarValidate
import com.denisolek.fleet.cars.infrastructure.CarEntity
import com.denisolek.fleet.cars.infrastructure.CarRepository
import com.denisolek.fleet.cars.model.CarFactory
import com.denisolek.fleet.cars.model.event.CarAdded
import com.denisolek.fleet.cars.model.event.CarUpdated
import com.denisolek.fleet.infrastructure.Globals
import com.denisolek.fleet.infrastructure.findOne
import com.denisolek.fleet.infrastructure.toCarEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CarEventHandler(
    val repository: CarRepository,
    val commandHandler: CarCommandHandler,
    val queryHanlder: CarQueryHandler
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [Globals.CARS_TOPIC])
    fun process(message: String) {
        val event = message.toCarEvent()
        when (event) {
            is CarAdded -> handle(event)
            is CarUpdated -> handle(event)
        }
    }

    fun handle(carAdded: CarAdded) {
        try {
            queryHanlder.validateAdd(AddCarValidate(carAdded.registrationNumber))
            val car = CarFactory.create(carAdded)
            repository.save(CarEntity(car))
            logger.info("[CarAdded] {${car.id.value}} - Handled")
        } catch (e: Exception) {
            commandHandler.handle(AddCarCancelledCommand(carAdded.aggregateId))
            throw e
        }
    }

    fun handle(carUpdated: CarUpdated) {
        try {
            queryHanlder.validateUpdate(UpdateCarValidate(carUpdated.registrationNumber), carUpdated.id)
            val car = repository.findByIdOrThrow(carUpdated.aggregateId).toDomainModel()
            car.apply(carUpdated)
            repository.save(CarEntity(car))
            logger.info("[CarUpdated] {${car.id.value}} - Handled")
        } catch (e: Exception) {
            val car = repository.findOne(carUpdated.aggregateId)?.toDomainModel()
            car?.run { commandHandler.handle(UpdateCarCancelledCommand(this)) }
            throw e
        }
    }
}