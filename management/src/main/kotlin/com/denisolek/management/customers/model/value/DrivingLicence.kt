package com.denisolek.management.customers.model.value

import com.denisolek.management.customers.infrastructure.CustomerExceptions.InvalidDrivingLicenceException

class DrivingLicence private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(licence: String): DrivingLicence {
            when {
                !validate(licence) -> throw InvalidDrivingLicenceException()
            }
            return DrivingLicence(licence)
        }

        private fun validate(input: String) = input.length in 2..50
    }
}