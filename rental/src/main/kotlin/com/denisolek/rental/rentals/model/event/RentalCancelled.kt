package com.denisolek.rental.rentals.model.event

import com.denisolek.rental.rentals.facade.command.CancelRentalCommand
import java.time.LocalDateTime
import java.util.*

class RentalCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "RentalCancelled"
) : RentalEvent {
    constructor(command: CancelRentalCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now()
    )
}