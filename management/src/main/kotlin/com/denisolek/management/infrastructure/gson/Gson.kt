package com.denisolek.management.infrastructure.gson

import com.denisolek.management.customers.model.event.CustomerEvent
import com.denisolek.management.infrastructure.gson.adapters.CustomerEventDeserializerAdapter
import com.denisolek.management.infrastructure.gson.adapters.LocalDateAdapter
import com.denisolek.management.infrastructure.gson.adapters.LocalDateTimeAdapter
import com.denisolek.management.infrastructure.gson.adapters.LocalTimeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object Gson {
    fun create(): Gson {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
        builder.registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        builder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        builder.registerTypeAdapter(CustomerEvent::class.java, CustomerEventDeserializerAdapter())
        return builder.create()
    }
}