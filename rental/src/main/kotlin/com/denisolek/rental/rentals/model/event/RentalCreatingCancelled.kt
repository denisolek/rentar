package com.denisolek.rental.rentals.model.event

import com.denisolek.rental.rentals.facade.command.CreateRentalCancelledCommand
import java.time.LocalDateTime
import java.util.*

class RentalCreatingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CarCreatingCancelled"
) : RentalEvent {
    constructor(command: CreateRentalCancelledCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now()
    )
}
