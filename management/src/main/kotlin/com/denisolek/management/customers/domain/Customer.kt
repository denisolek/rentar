package com.denisolek.management.customers.domain

import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.domain.value.*
import java.time.LocalDateTime

class Customer(
    val id: CustomerId,
    val name: Name,
    val email: Email,
    val phoneNumber: PhoneNumber,
    val birthDate: BirthDate,
    val drivingLicence: DrivingLicence,
    val passport: Passport,
    val createdAt: LocalDateTime
)

object CustomerFactory {
    fun create(e: CustomerAdded) = Customer(
        id = CustomerId(),
        name = Name(e.firstName, e.lastName),
        email = Email(e.email),
        phoneNumber = PhoneNumber(e.phoneNumber),
        birthDate = BirthDate(e.birthDate),
        drivingLicence = DrivingLicence(e.drivingLicence),
        passport = Passport(e.passport),
        createdAt = e.occurredAt
    )
}