package com.denisolek.fleet.cars.model.event

import com.denisolek.fleet.cars.facade.command.AddCarCancelledCommand
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
}