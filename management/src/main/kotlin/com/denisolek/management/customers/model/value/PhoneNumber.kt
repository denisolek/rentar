package com.denisolek.management.customers.model.value

import com.denisolek.management.customers.facade.CustomerExceptions.InvalidPhoneNumberException
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

class PhoneNumber private constructor(
    val countryCode: Int,
    val nationalNumber: Long
) {
    companion object {
        operator fun invoke(number: String): PhoneNumber {
            val phoneUtil = PhoneNumberUtil.getInstance()
            val numberProto = try {
                phoneUtil.parse(number, "PL")
            } catch (e: NumberParseException) {
                throw InvalidPhoneNumberException(e.errorType)
            }
            if (!phoneUtil.isValidNumber(numberProto))
                throw InvalidPhoneNumberException()
            return PhoneNumber(numberProto.countryCode, numberProto.nationalNumber)
        }
    }
}

fun PhoneNumber?.toDTO() = this?.let { "+${this.countryCode}${this.nationalNumber}" } ?: ""