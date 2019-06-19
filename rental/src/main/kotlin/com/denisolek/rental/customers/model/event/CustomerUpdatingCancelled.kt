package com.denisolek.rental.customers.model.event

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
) : CustomerEvent