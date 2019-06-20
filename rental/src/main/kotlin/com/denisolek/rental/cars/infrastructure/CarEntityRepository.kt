package com.denisolek.rental.cars.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarEntityRepository : JpaRepository<CarEntity, UUID> {
    fun countById(id: UUID): Int
}