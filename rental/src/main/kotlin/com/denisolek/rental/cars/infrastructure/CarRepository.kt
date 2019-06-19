package com.denisolek.rental.cars.infrastructure

import com.denisolek.rental.cars.infrastructure.CarExceptions.CarNotFoundException
import com.denisolek.rental.cars.model.Car
import com.denisolek.rental.infrastructure.findOne
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarRepository : JpaRepository<CarEntity, UUID> {
    fun countById(id: UUID): Int

    fun save(car: Car): Car {
        return save(CarEntity(car)).toDomainModel()
    }

    fun findByIdOrThrow(id: UUID): Car {
        return findOne(id)?.toDomainModel() ?: throw CarNotFoundException()
    }

    fun removeIfExists(id: UUID) {
        delete(findOne(id) ?: throw CarNotFoundException())
    }

    fun existOrThrow(id: UUID): Boolean {
        if (countById(id) > 0)
            return true
        else throw CarNotFoundException()
    }
}