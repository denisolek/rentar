package com.denisolek.fleet.cars.infrastructure

import com.denisolek.fleet.infrastructure.ServiceException
import org.springframework.http.HttpStatus

class CarExceptions {
    class InvalidFuelException: ServiceException(HttpStatus.BAD_REQUEST, "Invalid fuel.")
    class InvalidManufacturerException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid manufacturer name.")
    class InvalidModelException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid model name.")
    class InvalidMileageException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid mileage name.")
    class InvalidProductionYearException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid production year name.")
    class InvalidRegistrationNumberException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid registration number name.")
    class InvalidTransmissionException : ServiceException(HttpStatus.BAD_REQUEST, "Invalid transmission name.")
    class CarNotFoundException : ServiceException(HttpStatus.NOT_FOUND, "Car not found.")
    class RegistrationNumberAlreadyExistsException : ServiceException(HttpStatus.CONFLICT, "Registration number already exists.")
}