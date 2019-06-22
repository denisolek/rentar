package com.denisolek.rental.rentals.infrastructure

import com.denisolek.rental.infrastructure.Exception
import org.springframework.http.HttpStatus

class RentalExceptions {
    class DomainMappingException : Exception(HttpStatus.INTERNAL_SERVER_ERROR, "Error mapping entity to domain model.")
    class RentalsOverlapsException : Exception(HttpStatus.BAD_REQUEST, "Rental overlaps existing.")
    class RentalInThePastException : Exception(HttpStatus.BAD_REQUEST, "Can't create rental in the past.")
    class RentalStartsAfterEndsException : Exception(HttpStatus.BAD_REQUEST, "Rental start date can't be after end date.")
}