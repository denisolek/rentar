package com.denisolek.fleet.cars.facade

import com.denisolek.fleet.cars.facade.query.AddCarValidate
import com.denisolek.fleet.cars.facade.query.BaseCar
import com.denisolek.fleet.cars.facade.query.UpdateCarValidate
import com.denisolek.fleet.cars.infrastructure.CarExceptions.RegistrationNumberAlreadyExistsException
import com.denisolek.fleet.cars.infrastructure.CarRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarQueryHandler(val repository: CarRepository) {
    fun validateAdd(dto: AddCarValidate) {
        if (repository.countByRegistrationNumber(dto.registrationNumber) > 0)
            throw RegistrationNumberAlreadyExistsException()
    }

    fun validateUpdate(dto: UpdateCarValidate, id: UUID) {
        val car = repository.findByIdOrThrow(id)
        if (car.registrationNumber != dto.registrationNumber &&
            repository.countByRegistrationNumber(dto.registrationNumber) > 0
        )
            throw RegistrationNumberAlreadyExistsException()
    }

    fun fetchAll(): List<BaseCar> {
        return repository.findAll().map { BaseCar(it.toDomainModel()) }
    }
}