package com.denisolek.rental.rentals.model.event

import com.denisolek.rental.rentals.facade.command.CreateRentalCommand
import com.denisolek.rental.rentals.model.value.RentalId
import java.time.LocalDateTime
import java.util.*

class RentalCreated(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "RentalCreated",
    val customerId: UUID,
    val carId: UUID,
    val from: LocalDateTime,
    val to: LocalDateTime
) : RentalEvent {
    constructor(command: CreateRentalCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = RentalId().value,
        occurredAt = LocalDateTime.now(),
        customerId = command.customerId,
        carId = command.carId,
        from = command.from,
        to = command.to
    )
}