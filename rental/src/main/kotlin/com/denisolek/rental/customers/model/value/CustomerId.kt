package com.denisolek.rental.customers.model.value

import java.io.Serializable
import java.util.*

data class CustomerId(
    val value: UUID
) : Serializable {
    constructor() : this(UUID.randomUUID())
}