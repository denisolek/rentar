package com.denisolek.management.customers.domain

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