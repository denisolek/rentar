package com.denisolek.management.customers.model.value

import com.denisolek.management.customers.infrastructure.CustomerExceptions.InvalidPassportException

class Passport private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(passport: String): Passport {
            when {
                !validate(passport) -> throw InvalidPassportException()
            }
            return Passport(passport)
        }

        private fun validate(input: String) = input.length in 2..50
    }

    fun equals(value: String) = this.value == value
}