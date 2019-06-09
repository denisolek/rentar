package com.denisolek.fleet.cars.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarRepository: JpaRepository<CarEntity, UUID> {
}