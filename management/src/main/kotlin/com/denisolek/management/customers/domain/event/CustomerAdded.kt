package com.denisolek.management.customers.domain.event

import com.denisolek.management.customers.dto.AddCustomerDTO
import com.denisolek.management.infrastructure.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CustomerAdded(
    override val id: UUID,
    override val aggregateId: UUID,
    override val occurredAt: LocalDateTime,
    override val type: String = "CustomerAdded",
    override val version: Int = 1,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) : CustomerEvent {
    constructor(dto: AddCustomerDTO) : this(
        id = UUID.randomUUID(),
        aggregateId = UUID.randomUUID(),
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