package com.denisolek.management.customers.facade.command

import com.denisolek.management.customers.model.Customer
import com.denisolek.management.customers.model.value.toDTO
import java.time.LocalDate
import java.util.*

class UpdateCustomerCancelledCommand(
    val aggregateId: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) {
    constructor(customer: Customer) : this(
        aggregateId = customer.id.value,
        firstName = customer.name.firstName,
        lastName = customer.name.lastName,
        email = customer.email.value,
        birthDate = customer.birthDate.value,
        phoneNumber = customer.phoneNumber.toDTO(),
        drivingLicence = customer.drivingLicence.value,
        passport = customer.passport.value
    )
}
