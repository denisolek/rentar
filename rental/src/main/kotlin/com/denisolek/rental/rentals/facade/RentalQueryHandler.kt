package com.denisolek.rental.rentals.facade

import com.denisolek.rental.cars.facade.CarFacade
import com.denisolek.rental.customers.facade.CustomerFacade
import com.denisolek.rental.infrastructure.isAfterOrEqual
import com.denisolek.rental.infrastructure.isBeforeOrEqual
import com.denisolek.rental.rentals.facade.query.BaseRental
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import com.denisolek.rental.rentals.facade.query.DetailedRental
import com.denisolek.rental.rentals.facade.query.RentalEstimate
import com.denisolek.rental.rentals.infrastructure.RentalExceptions.*
import com.denisolek.rental.rentals.infrastructure.RentalRepository
import com.denisolek.rental.rentals.model.value.RentalPrice
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class RentalQueryHandler(
    val repository: RentalRepository,
    val carFacade: CarFacade,
    val customerFacade: CustomerFacade
) {
    fun validateCreate(dto: CreateRentalValidate) {
        timeValidation(dto.from, dto.to)
        carExists(dto)
        customerExists(dto)
        rentalsNotOverlapping(dto)
    }

    private fun customerExists(dto: CreateRentalValidate) {
        customerFacade.existOrThrow(dto.customerId)
    }

    private fun carExists(dto: CreateRentalValidate) {
        carFacade.existOrThrow(dto.carId)
    }

    private fun rentalsNotOverlapping(dto: CreateRentalValidate) {
        repository.findByCar(dto.carId).forEach {
            if (it.overlaps(dto.from, dto.to)) throw RentalsOverlapsException()
        }
    }

    private fun timeValidation(from: LocalDateTime, to: LocalDateTime) {
        if (from.isBeforeOrEqual(LocalDateTime.now()))
            throw RentalInThePastException()
        if (from.isAfterOrEqual(to))
            throw RentalStartsAfterEndsException()
    }

    fun fetchAll(): List<BaseRental> {
        return repository.findAll().map { BaseRental(it) }
    }

    fun findOne(id: UUID): DetailedRental {
        return DetailedRental(repository.findOneOrThrow(id))
    }

    fun estimate(from: LocalDateTime, to: LocalDateTime): List<RentalEstimate> {
        timeValidation(from, to)
        val overlappingCars = repository.findAll().filter { it.overlaps(from, to) }.map { it.carId }
        val availableCars = carFacade.fetchAll().filter { !overlappingCars.contains(it.id) }
        return availableCars.map {
            RentalEstimate(
                carName = it.name.value,
                price = RentalPrice(it.dailyPrice, from, to).toDto(),
                from = from,
                to = to
            )
        }
    }

    fun fetchForCustomer(id: UUID): List<BaseRental> {
        return repository.findByCustomer(id).map { BaseRental(it) }
    }

    fun fetchForCar(id: UUID): List<BaseRental> {
        return repository.findByCar(id).map { BaseRental(it) }
    }
}
