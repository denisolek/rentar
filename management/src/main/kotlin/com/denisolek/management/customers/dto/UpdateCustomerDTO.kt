package com.denisolek.management.customers.dto

import com.denisolek.management.customers.domain.value.*
import java.time.LocalDate

class UpdateCustomerDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) {
    fun validate() {
        Name(firstName, lastName)
        Email(email)
        BirthDate(birthDate)
        PhoneNumber(phoneNumber)
        DrivingLicence(drivingLicence)
        Passport(passport)
    }
}
