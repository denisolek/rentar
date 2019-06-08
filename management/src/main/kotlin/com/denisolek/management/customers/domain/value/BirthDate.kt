package com.denisolek.management.customers.domain.value

import com.denisolek.management.customers.dto.CustomerExceptions.InvalidBirthDate
import java.time.LocalDate

class BirthDate private constructor(
    val value: LocalDate
) {
    companion object {
        operator fun invoke(date: LocalDate): BirthDate {
            validate(date)
            return BirthDate(date)
        }

        private fun validate(date: LocalDate) {
            val valid = date.isBefore(LocalDate.now())
            if (!valid) throw InvalidBirthDate()
        }
    }
}