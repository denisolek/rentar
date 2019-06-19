package com.denisolek.rental.rentals.infrastructure

import com.denisolek.rental.rentals.model.Rental
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RentalRepository : JpaRepository<RentalEntity, UUID> {
    fun findByCarId(id: UUID): List<RentalEntity>

    fun findByCar(id: UUID): List<Rental> {
        return findByCarId(id).map { it.toDomainModel() }
    }

    fun save(rental: Rental): Rental {
        return save(RentalEntity(rental)).toDomainModel()
    }
}