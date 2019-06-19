package com.denisolek.rental.infrastructure.gson.adapters

import com.denisolek.rental.cars.model.event.CarAdded
import com.denisolek.rental.cars.model.event.CarAddingCancelled
import com.denisolek.rental.cars.model.event.CarUpdated
import com.denisolek.rental.cars.model.event.CarUpdatingCancelled
import com.denisolek.rental.customers.model.event.CustomerAdded
import com.denisolek.rental.customers.model.event.CustomerAddingCancelled
import com.denisolek.rental.customers.model.event.CustomerUpdated
import com.denisolek.rental.customers.model.event.CustomerUpdatingCancelled
import com.denisolek.rental.infrastructure.Exception
import com.denisolek.rental.rentals.model.event.RentalCreated
import com.denisolek.rental.rentals.model.event.RentalCreatingCancelled
import com.google.gson.*
import org.springframework.http.HttpStatus
import java.lang.reflect.Type

class EventDeserializerAdapter : JsonDeserializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Any {
        val jsonObject = json.asJsonObject
        val prim = jsonObject.get("type") as JsonPrimitive
        val objectClass = when (prim.asString) {
            RentalCreated::class.java.simpleName -> getObjectClass(RentalCreated::class.java.name)
            RentalCreatingCancelled::class.java.simpleName -> getObjectClass(RentalCreatingCancelled::class.java.name)
            CarAdded::class.java.simpleName -> getObjectClass(CarAdded::class.java.name)
            CarAddingCancelled::class.java.simpleName -> getObjectClass(CarAddingCancelled::class.java.name)
            CarUpdated::class.java.simpleName -> getObjectClass(CarUpdated::class.java.name)
            CarUpdatingCancelled::class.java.simpleName -> getObjectClass(CarUpdatingCancelled::class.java.name)
            CustomerAdded::class.java.simpleName -> getObjectClass(CustomerAdded::class.java.name)
            CustomerAddingCancelled::class.java.simpleName -> getObjectClass(CustomerAddingCancelled::class.java.name)
            CustomerUpdated::class.java.simpleName -> getObjectClass(CustomerUpdated::class.java.name)
            CustomerUpdatingCancelled::class.java.simpleName -> getObjectClass(CustomerUpdatingCancelled::class.java.name)
            else -> throw Exception(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event.")
        }
        return context.deserialize(jsonObject, objectClass)
    }

    private fun getObjectClass(className: String): Class<*> {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e.message)
        }
    }
}