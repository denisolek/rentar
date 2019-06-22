package com.denisolek.rental.rentals.facade

import com.denisolek.rental.cars.facade.CarFacade
import com.denisolek.rental.customers.facade.CustomerFacade
import com.denisolek.rental.infrastructure.isAfterOrEqual
import com.denisolek.rental.infrastructure.isBeforeOrEqual
import com.denisolek.rental.rentals.facade.query.BaseRental
import com.denisolek.rental.rentals.facade.query.CreateRentalValidate
import com.denisolek.rental.rentals.facade.query.DetailedRental
import com.denisolek.rental.rentals.infrastructure.RentalExceptions.*
import com.denisolek.rental.rentals.infrastructure.RentalRepository
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
        timeValidation(dto)
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

    private fun timeValidation(dto: CreateRentalValidate) {
        if (dto.from.isBeforeOrEqual(LocalDateTime.now()))
            throw RentalInThePastException()
        if (dto.from.isAfterOrEqual(dto.to))
            throw RentalStartsAfterEndsException()
    }

    fun fetchAll(): List<BaseRental> {
        return repository.findAll().map { BaseRental(it) }
    }

    fun findOne(id: UUID): DetailedRental {
        return DetailedRental(repository.findOneOrThrow(id))
    }
}
