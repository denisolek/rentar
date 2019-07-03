package com.denisolek.fleet.cars.facade

import com.denisolek.fleet.cars.facade.query.AddCarValidate
import com.denisolek.fleet.cars.facade.query.BaseCar
import com.denisolek.fleet.cars.facade.query.DetailedCar
import com.denisolek.fleet.cars.facade.query.UpdateCarValidate
import com.denisolek.fleet.cars.infrastructure.CarExceptions.RegistrationNumberAlreadyExistsException
import com.denisolek.fleet.cars.infrastructure.CarRepository
import com.denisolek.fleet.cars.model.value.CarId
import com.denisolek.fleet.cars.model.value.RegistrationNumber
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarQueryHandler(val repository: CarRepository) {
    fun validateAdd(dto: AddCarValidate) {
        repository.registrationAvailableOrThrow(RegistrationNumber(dto.registrationNumber))
    }

    fun validateUpdate(dto: UpdateCarValidate, id: UUID) {
        repository.findByRegistration(RegistrationNumber(dto.registrationNumber))
            .firstOrNull()
            ?.let { if (it.id != CarId(id)) throw RegistrationNumberAlreadyExistsException() }
    }

    fun fetchAll(): List<BaseCar> {
        return repository.findAll().map { BaseCar(it) }
    }

    fun fetchOne(id: UUID): DetailedCar {
        return DetailedCar(repository.findByIdOrThrow(id))
    }
}