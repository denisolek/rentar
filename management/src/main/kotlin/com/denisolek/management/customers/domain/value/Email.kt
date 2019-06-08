package com.denisolek.management.customers.domain.value

import com.denisolek.management.customers.dto.CustomerExceptions.InvalidEmailException

class Email private constructor(
    val value: String
) {
    companion object {
        operator fun invoke(email: String): Email {
            val lowered = email.toLowerCase()
            validate(lowered)
            return Email(lowered)
        }

        private fun validate(email: String) {
            val valid = email.matches(
                ("^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@" +
                        "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$").toRegex()
            )
            if (!valid) throw InvalidEmailException()
        }
    }
}