package com.denisolek.fleet.cars.infrastructure

import com.denisolek.fleet.cars.infrastructure.CarExceptions.CarNotFoundException
import com.denisolek.fleet.cars.infrastructure.CarExceptions.RegistrationNumberAlreadyExistsException
import com.denisolek.fleet.infrastructure.findOne
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarRepository : JpaRepository<CarEntity, UUID> {
    fun countByRegistrationNumber(registrationNumber: String): Int

    @JvmDefault
    fun findByIdOrThrow(id: UUID): CarEntity {
        return findOne(id) ?: throw CarNotFoundException()
    }

    fun registrationAvailableOrThrow(registrationNumber: String): Boolean {
        if (countByRegistrationNumber(registrationNumber) > 0)
            throw RegistrationNumberAlreadyExistsException()
        else return true
    }
}