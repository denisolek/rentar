package com.denisolek.rental.rentals.infrastructure

import com.denisolek.rental.infrastructure.findOne
import com.denisolek.rental.rentals.infrastructure.RentalExceptions.RentalNotFoundException
import com.denisolek.rental.rentals.model.Rental
import org.springframework.stereotype.Component
import java.util.*

@Component
class RentalRepository(val repository: RentalEntityRepository) {
    fun findByCar(id: UUID): List<Rental> {
        return repository.findByCarId(id).map { it.toDomainModel() }
    }

    fun save(rental: Rental): Rental {
        return repository.save(RentalEntity(rental)).toDomainModel()
    }

    fun findAll(): List<Rental> {
        return repository.findAll().map { it.toDomainModel() }
    }

    fun findOneOrThrow(id: UUID): Rental {
        return repository.findOne(id)?.toDomainModel() ?: throw RentalNotFoundException()
    }

    fun findByCustomer(id: UUID): List<Rental> {
        return repository.findByCustomerId(id).map { it.toDomainModel() }
    }
}