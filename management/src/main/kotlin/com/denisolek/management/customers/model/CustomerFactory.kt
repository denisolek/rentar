package com.denisolek.management.customers.model

import com.denisolek.management.customers.model.event.CustomerAdded
import com.denisolek.management.customers.model.value.*
import com.denisolek.management.customers.model.Customer

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