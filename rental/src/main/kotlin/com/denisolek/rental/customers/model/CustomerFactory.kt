package com.denisolek.rental.customers.model

import com.denisolek.rental.customers.model.event.CustomerAdded
import com.denisolek.rental.customers.model.value.CustomerId

object CustomerFactory {
    fun create(e: CustomerAdded) = Customer(
        id = CustomerId(e.aggregateId)
    )
}