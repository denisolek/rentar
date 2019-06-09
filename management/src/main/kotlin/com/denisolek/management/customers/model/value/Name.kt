package com.denisolek.management.customers.model.value

import com.denisolek.management.customers.facade.CustomerExceptions.InvalidFirstNameException
import com.denisolek.management.customers.facade.CustomerExceptions.InvalidLastNameException

class Name private constructor(
    val firstName: String,
    val lastName: String
) {
    companion object {
        operator fun invoke(firstName: String, lastName: String): Name {
            when {
                !validate(firstName) -> throw InvalidFirstNameException()
                !validate(lastName) -> throw InvalidLastNameException()
            }
            return Name(firstName, lastName)
        }

        private fun validate(input: String) = input.length in 2..50
    }
}