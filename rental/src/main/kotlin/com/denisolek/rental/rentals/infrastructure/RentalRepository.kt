package com.denisolek.rental.rentals.infrastructure

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
}