package com.denisolek.rental.cars.facade

import com.denisolek.rental.cars.infrastructure.CarRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CarFacade(val repository: CarRepository) {
    fun existOrThrow(id: UUID) = repository.existOrThrow(id)
    fun fetchOne(id: UUID) = repository.findByIdOrThrow(id)
    fun fetchAll() = repository.findAll()
}