package com.denisolek.rental.customers.model.event

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
) : CustomerEvent