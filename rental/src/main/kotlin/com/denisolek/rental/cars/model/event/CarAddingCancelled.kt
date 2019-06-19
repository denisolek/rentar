package com.denisolek.rental.cars.model.event

import java.time.LocalDateTime
import java.util.*

class CarAddingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarAddingCancelled"
) : CarEvent