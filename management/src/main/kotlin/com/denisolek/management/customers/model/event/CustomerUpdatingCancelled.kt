package com.denisolek.management.customers.model.event

import com.denisolek.management.customers.facade.command.UpdateCustomerCancelledCommand
import com.denisolek.management.infrastructure.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CustomerUpdatingCancelled(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val version: Int = 1,
    override val type: String = "CustomerUpdatingCancelled",
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) : CustomerEvent {
    constructor(command: UpdateCustomerCancelledCommand) : this(
        id = UUID.randomUUID(),
        aggregateId = command.aggregateId,
        occurredAt = LocalDateTime.now(),
        firstName = command.firstName,
        lastName = command.lastName,
        email = command.email,
        birthDate = command.birthDate,
        phoneNumber = command.phoneNumber,
        drivingLicence = command.drivingLicence,
        passport = command.passport
    )

    override fun toJson(): String = Gson.create().toJson(this)
}
