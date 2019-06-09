package com.denisolek.management.customers.domain

import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.domain.value.*

object CustomerFactory {
    fun create(e: CustomerAdded) = Customer(
        id = CustomerId(e.aggregateId),
        name = Name(e.firstName, e.lastName),
        email = Email(e.email),
        phoneNumber = PhoneNumber(e.phoneNumber),
        birthDate = BirthDate(e.birthDate),
        drivingLicence = DrivingLicence(e.drivingLicence),
        passport = Passport(e.passport),
        createdAt = e.occurredAt,
        updatedAt = e.occurredAt
    )
}