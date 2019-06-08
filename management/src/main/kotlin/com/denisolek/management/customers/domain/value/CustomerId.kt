package com.denisolek.management.customers.domain.value

import java.io.Serializable
import java.util.*

data class CustomerId(
    val value: UUID
) : Serializable {
    constructor() : this(UUID.randomUUID())
}