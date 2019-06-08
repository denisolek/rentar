package com.denisolek.management.customers.dto

import com.denisolek.management.infrastructure.ServiceException
import com.google.i18n.phonenumbers.NumberParseException.ErrorType
import org.springframework.http.HttpStatus

class CustomerExceptions {
    class InvalidFirstNameException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid first name.")
    class InvalidLastNameException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid last name.")
    class InvalidEmailException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid email.")
    class InvalidBirthDate : ServiceException(HttpStatus.BAD_REQUEST, "Invalid birth date.")
    class InvalidPhoneNumberException(errorType: ErrorType? = null) :
        ServiceException(HttpStatus.BAD_REQUEST, "Invalid phone number $errorType")
    class InvalidPassportException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid passport.")
    class InvalidDrivingLicenceException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid driving licence.")
}