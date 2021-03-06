package com.denisolek.rental.rentals.facade

import com.denisolek.rental.cars.facade.CarFacade
import com.denisolek.rental.infrastructure.Globals
import com.denisolek.rental.infrastructure.toRentalEvent
import com.denisolek.rental.rentals.facade.command.CreateRentalCancelledCommand
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import com.denisolek.rental.rentals.infrastructure.RentalRepository
import com.denisolek.rental.rentals.model.CancelledRental
import com.denisolek.rental.rentals.model.RentalFactory
import com.denisolek.rental.rentals.model.event.RentalCancelled
import com.denisolek.rental.rentals.model.event.RentalCreated
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class RentalEventHandler(
    val repository: RentalRepository,
    val commandHandler: RentalCommandHandler,
    val queryHandler: RentalQueryHandler,
    val carFacade: CarFacade
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = [Globals.RENTALS_TOPIC])
    fun process(message: String) {
        val event = message.toRentalEvent()
        when (event) {
            is RentalCreated -> handle(event)
            is RentalCancelled -> handle(event)
        }
    }

    fun handle(rentalCreated: RentalCreated) {
        try {
            queryHandler.validateCreate(
                CreateRentalValidate(
                    rentalCreated.carId,
                    rentalCreated.customerId,
                    rentalCreated.from,
                    rentalCreated.to
                )
            )
            val dailyPrice = carFacade.fetchOne(rentalCreated.carId).dailyPrice
            RentalFactory.create(rentalCreated, dailyPrice).run {
                repository.save(this)
                logger.info("[RentalCreated] {${this.id.value}} - Handled")
            }
        } catch (e: Exception) {
            commandHandler.handle(CreateRentalCancelledCommand(rentalCreated.aggregateId))
            throw e
        }
    }

    fun handle(rentalCancelled: RentalCancelled) {
        val rental = repository.findOneOrThrow(rentalCancelled.aggregateId)
        repository.save(CancelledRental(rental))
        logger.info("[RentalCancelled] {${rental.id.value} - Handled")
    }
}