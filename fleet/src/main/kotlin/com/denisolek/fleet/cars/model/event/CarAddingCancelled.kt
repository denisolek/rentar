package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.cars.facade.command.AddCarCancelledCommand
import com.denisolek.fleet.infrastructure.gson.Gson
import java.time.LocalDateTime
import java.util.*

class CarAddingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarAddingCancelled"
) : CarEvent {
    constructor(command: AddCarCancelledCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now()
    )

    override fun toJson(): String = Gson.create().toJson(this)
}