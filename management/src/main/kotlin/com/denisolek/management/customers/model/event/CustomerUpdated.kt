package com.denisolek.management.customers.model.event

import com.denisolek.management.customers.model.value.CustomerId
import com.denisolek.management.customers.facade.command.UpdateCustomerCommand
import com.denisolek.management.infrastructure.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CustomerUpdated(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val type: String = "CustomerUpdated",
    override val version: Int = 1,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) : CustomerEvent {
    constructor(dto: UpdateCustomerCommand, id: CustomerId) : this(
        id = UUID.randomUUID(),
        aggregateId = id.value,
        occurredAt = LocalDateTime.now(),
        firstName = dto.firstName,
        lastName = dto.lastName,
        email = dto.email,
        birthDate = dto.birthDate,
        phoneNumber = dto.phoneNumber,
        drivingLicence = dto.drivingLicence,
        passport = dto.passport
    )

    override fun toJson(): String = Gson.create().toJson(this)
}