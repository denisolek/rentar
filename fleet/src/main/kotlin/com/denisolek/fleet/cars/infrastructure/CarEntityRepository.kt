package com.denisolek.fleet.cars.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarEntityRepository : JpaRepository<CarEntity, UUID> {
    fun countByRegistrationNumber(registrationNumber: String): Int
    fun findByRegistrationNumber(registrationNumber: String): List<CarEntity>
}