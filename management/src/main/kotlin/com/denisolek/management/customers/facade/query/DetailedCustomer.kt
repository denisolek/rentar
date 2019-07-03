package com.denisolek.management.customers.facade.query

import com.denisolek.management.customers.model.Customer
import com.denisolek.management.customers.model.value.toDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class DetailedCustomer(
    val id: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) {
    constructor(customer: Customer) : this(
        id = customer.id.value,
        createdAt = customer.createdAt,
        updatedAt = customer.updatedAt,
        firstName = customer.name.firstName,
        lastName = customer.name.lastName,
        email = customer.email.value,
        birthDate = customer.birthDate.value,
        phoneNumber = customer.phoneNumber.toDTO(),
        drivingLicence = customer.drivingLicence.value,
        passport = customer.passport.value
    )
}