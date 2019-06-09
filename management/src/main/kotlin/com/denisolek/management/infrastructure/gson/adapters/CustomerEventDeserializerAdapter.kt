package com.denisolek.management.infrastructure.gson.adapters

import com.denisolek.management.customers.model.event.CustomerAdded
import com.denisolek.management.customers.model.event.CustomerAddingCancelled
import com.denisolek.management.customers.model.event.CustomerUpdated
import com.denisolek.management.customers.model.event.CustomerUpdatingCancelled
import com.denisolek.management.infrastructure.ServiceException
import com.google.gson.*
import org.springframework.http.HttpStatus
import java.lang.reflect.Type

class CustomerEventDeserializerAdapter : JsonDeserializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Any {
        val jsonObject = json.asJsonObject
        val prim = jsonObject.get("type") as JsonPrimitive
        val objectClass = when (prim.asString) {
            CustomerAdded::class.java.simpleName -> getObjectClass(CustomerAdded::class.java.name)
            CustomerAddingCancelled::class.java.simpleName -> getObjectClass(CustomerAddingCancelled::class.java.name)
            CustomerUpdated::class.java.simpleName -> getObjectClass(CustomerUpdated::class.java.name)
            CustomerUpdatingCancelled::class.java.simpleName -> getObjectClass(CustomerUpdatingCancelled::class.java.name)
            else -> throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported event.")
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