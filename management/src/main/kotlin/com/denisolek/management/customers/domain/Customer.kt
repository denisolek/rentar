package com.denisolek.management.customers.domain

import com.denisolek.management.customers.domain.event.CustomerUpdated
import com.denisolek.management.customers.domain.value.*
import java.time.LocalDateTime

class Customer(
    val id: CustomerId,
    var name: Name,
    var email: Email,
    var phoneNumber: PhoneNumber,
    var birthDate: BirthDate,
    var drivingLicence: DrivingLicence,
    var passport: Passport,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
    fun apply(e: CustomerUpdated) {
        this.name = Name(e.firstName, e.lastName)
        this.email = Email(e.email)
        this.phoneNumber = PhoneNumber(e.phoneNumber)
        this.birthDate = BirthDate(e.birthDate)
        this.drivingLicence = DrivingLicence(e.drivingLicence)
        this.passport = Passport(e.passport)
        this.updatedAt = e.occurredAt
    }
}