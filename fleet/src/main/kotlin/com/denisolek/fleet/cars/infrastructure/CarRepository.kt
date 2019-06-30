package com.denisolek.fleet.cars.infrastructure

import com.denisolek.fleet.cars.model.Car
import com.denisolek.fleet.cars.model.value.RegistrationNumber
import com.denisolek.fleet.infrastructure.findOne
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarRepository(val repository: CarEntityRepository) {
    fun save(car: Car): Car {
        return repository.save(CarEntity(car)).toDomainModel()
    }

    fun findAll(): List<Car> {
        return repository.findAll().map { it.toDomainModel() }
    }

    fun findByRegistration(registrationNumber: RegistrationNumber): List<Car> {
        return repository.findByRegistrationNumber(registrationNumber.value).map { it.toDomainModel() }
    }

    fun findByIdOrThrow(id: UUID): Car {
        return repository.findOne(id)?.toDomainModel() ?: throw CarExceptions.CarNotFoundException()
    }

    fun registrationAvailableOrThrow(registrationNumber: RegistrationNumber): Boolean {
        if (repository.countByRegistrationNumber(registrationNumber.value) > 0)
            throw CarExceptions.RegistrationNumberAlreadyExistsException()
        else return true
    }
}