package com.denisolek.management.customers.model.event

import com.denisolek.management.customers.facade.command.AddCustomerCancelledCommand
import com.denisolek.management.infrastructure.gson.Gson
import java.time.LocalDateTime
import java.util.*

class CustomerAddingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CustomerAddingCancelled"
) : CustomerEvent {
    constructor(command: AddCustomerCancelledCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now()
    )

    override fun toJson(): String = Gson.create().toJson(this)
}
