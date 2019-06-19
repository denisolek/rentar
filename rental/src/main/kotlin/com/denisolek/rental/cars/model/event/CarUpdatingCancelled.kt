package com.denisolek.rental.cars.model.event

import java.time.LocalDateTime
import java.util.*

class CarUpdatingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarUpdatingCancelled",
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String,
    val dailyPrice: Int
) : CarEvent