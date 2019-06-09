package com.denisolek.management.customers.facade.query

import com.denisolek.management.customers.model.Customer
import com.denisolek.management.customers.model.value.toDTO
import java.util.*

class BaseCustomer(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val drivingLicence: String
) {
    constructor(customer: Customer) : this(
        id = customer.id.value,
        firstName = customer.name.firstName,
        lastName = customer.name.lastName,
        phoneNumber = customer.phoneNumber.toDTO(),
        drivingLicence = customer.drivingLicence.value
    )
}