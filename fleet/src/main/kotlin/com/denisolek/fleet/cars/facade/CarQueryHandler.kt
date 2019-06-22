package com.denisolek.fleet.cars.facade

import com.denisolek.fleet.cars.facade.query.AddCarValidate
import com.denisolek.fleet.cars.facade.query.BaseCar
import com.denisolek.fleet.cars.facade.query.DetailedCar
import com.denisolek.fleet.cars.facade.query.UpdateCarValidate
import com.denisolek.fleet.cars.infrastructure.CarRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarQueryHandler(val repository: CarRepository) {
    fun validateAdd(dto: AddCarValidate) {
        repository.registrationAvailableOrThrow(dto.registrationNumber)
    }

    fun validateUpdate(dto: UpdateCarValidate, id: UUID) {
        val car = repository.findByIdOrThrow(id)
        if (car.registrationNumber.equals(dto.registrationNumber)) {
            repository.registrationAvailableOrThrow(dto.registrationNumber)
        }
    }

    fun fetchAll(): List<BaseCar> {
        return repository.findAll().map { BaseCar(it) }
    }

    fun fetchOne(id: UUID): DetailedCar {
        return DetailedCar(repository.findByIdOrThrow(id))
    }
}