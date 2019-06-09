package com.denisolek.fleet.cars.infrastructure

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CarEntity(
    @Id
    val id: UUID,
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    @Column(unique = true)
    val registrationNumber: String
)