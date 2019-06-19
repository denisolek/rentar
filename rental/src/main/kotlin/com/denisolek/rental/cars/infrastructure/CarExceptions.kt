package com.denisolek.rental.cars.infrastructure

import com.denisolek.rental.infrastructure.Exception
import org.springframework.http.HttpStatus

class CarExceptions {
    class InvalidCarName : Exception(HttpStatus.BAD_REQUEST, "Invalid car name.")
    class InvalidDailyPrice : Exception(HttpStatus.BAD_REQUEST, "Invalid daily price.")
    class CarNotFoundException : Exception(HttpStatus.BAD_REQUEST, "Car not found.")
}