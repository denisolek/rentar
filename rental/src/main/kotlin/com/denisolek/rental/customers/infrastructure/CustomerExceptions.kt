package com.denisolek.rental.customers.infrastructure

import com.denisolek.rental.infrastructure.Exception
import org.springframework.http.HttpStatus

class CustomerExceptions {
    class CustomerNotFoundException : Exception(HttpStatus.BAD_REQUEST, "Customer not found.")
}