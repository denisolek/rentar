package com.denisolek.rental.rentals.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RentalEntityRepository : JpaRepository<RentalEntity, UUID> {
    fun findByCarId(id: UUID): List<RentalEntity>
}