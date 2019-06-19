package com.denisolek.rental.customers.infrastructure

import com.denisolek.rental.customers.model.Customer
import com.denisolek.rental.customers.model.value.CustomerId
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CustomerEntity(@Id val id: UUID) {
    constructor(customer: Customer) : this(
        id = customer.id.value
    )

    fun toDomainModel() = Customer(CustomerId(this.id))
}