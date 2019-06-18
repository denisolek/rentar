package com.denisolek.fleet.cars.infrastructure

import com.denisolek.fleet.infrastructure.Exception
import org.springframework.http.HttpStatus

class CarExceptions {
    class InvalidFuelException: Exception(HttpStatus.BAD_REQUEST, "Invalid fuel.")
    class InvalidManufacturerException : Exception(HttpStatus.BAD_REQUEST, "Invalid manufacturer name.")
    class InvalidModelException : Exception(HttpStatus.BAD_REQUEST, "Invalid model name.")
    class InvalidMileageException : Exception(HttpStatus.BAD_REQUEST, "Invalid mileage.")
    class InvalidProductionYearException : Exception(HttpStatus.BAD_REQUEST, "Invalid production year.")
    class InvalidRegistrationNumberException : Exception(HttpStatus.BAD_REQUEST, "Invalid registration number.")
    class InvalidTransmissionException : Exception(HttpStatus.BAD_REQUEST, "Invalid transmission.")
    class InvalidSegmentException : Exception(HttpStatus.BAD_REQUEST, "Invalid segment.")
    class CarNotFoundException : Exception(HttpStatus.NOT_FOUND, "Car not found.")
    class RegistrationNumberAlreadyExistsException : Exception(HttpStatus.CONFLICT, "Registration number already exists.")
}