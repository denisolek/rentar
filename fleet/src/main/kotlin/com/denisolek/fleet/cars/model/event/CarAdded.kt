package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.infrastructure.gson.Gson
import java.time.LocalDateTime
import java.util.*

class CarAdded(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarAdded"
) : CarEvent {
    override fun toJson(): String = Gson.create().toJson(this)
}