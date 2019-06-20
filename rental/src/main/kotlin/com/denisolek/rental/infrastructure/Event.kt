package com.denisolek.rental.infrastructure

import com.denisolek.rental.infrastructure.gson.Gson
import java.time.LocalDateTime
import java.util.*

interface Event {
    val id: UUID
    val aggregateId: UUID
    val occurredAt: LocalDateTime
    val version: Int
    val type: String

    fun toJson(): String = Gson.create().toJson(this)
}