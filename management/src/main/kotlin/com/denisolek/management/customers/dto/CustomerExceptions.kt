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
    class EmailAlreadyExistsException: ServiceException(HttpStatus.CONFLICT, "Email already exists.")
    class DrivingLicenceAlreadyExistsException: ServiceException(HttpStatus.CONFLICT, "Driving licence already exists.")
    class PassportAlreadyExistsException: ServiceException(HttpStatus.CONFLICT, "Passport already exists.")
    class CustomerNotFoundException: ServiceException(HttpStatus.NOT_FOUND, "Customer not found.")
}