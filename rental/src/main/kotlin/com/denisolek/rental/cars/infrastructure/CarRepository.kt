package com.denisolek.rental.cars.infrastructure

import com.denisolek.rental.cars.model.Car
import com.denisolek.rental.infrastructure.findOne
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarRepository(val repository: CarEntityRepository) {
    fun save(car: Car): Car {
        return repository.save(CarEntity(car)).toDomainModel()
    }

    fun findByIdOrThrow(id: UUID): Car {
        return repository.findOne(id)?.toDomainModel() ?: throw CarExceptions.CarNotFoundException()
    }

    fun removeOrThrow(id: UUID) {
        return repository.delete(repository.findOne(id) ?: throw CarExceptions.CarNotFoundException())
    }

    fun existOrThrow(id: UUID): Boolean {
        if (repository.countById(id) > 0)
            return true
        else throw CarExceptions.CarNotFoundException()
    }

    fun findAll(): List<Car> {
        return repository.findAll().map { it.toDomainModel() }
    }
}