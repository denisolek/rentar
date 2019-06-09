package com.denisolek.management.customers.facade.command

import java.time.LocalDate

class AddCustomerCommand(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
)