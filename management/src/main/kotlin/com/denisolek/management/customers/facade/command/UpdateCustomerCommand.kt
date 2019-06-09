package com.denisolek.management.customers.facade.command

import java.time.LocalDate

class UpdateCustomerCommand(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) {
    constructor(command: UpdateCustomerCancelledCommand) : this(
        firstName = command.firstName,
        lastName = command.lastName,
        email = command.email,
        birthDate = command.birthDate,
        phoneNumber = command.phoneNumber,
        drivingLicence = command.drivingLicence,
        passport = command.passport
    )
}