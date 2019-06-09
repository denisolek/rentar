package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.query.AddCustomerValidate
import com.denisolek.management.customers.facade.query.CustomerQueryHandler
import com.denisolek.management.customers.facade.query.UpdateCustomerValidate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CustomerQueryController(val queryHandler: CustomerQueryHandler) {
    companion object {
        const val CUSTOMER_ID = "customerId"
    }

    @GetMapping("/customers/validateAdd")
    fun validateAdd(@RequestBody dto: AddCustomerValidate) {
        queryHandler.validateAdd(dto)
    }

    @GetMapping("/customers/{$CUSTOMER_ID}/validateUpdate")
    fun validateUpdate(
        @PathVariable(required = true, value = CUSTOMER_ID) id: UUID,
        @RequestBody dto: UpdateCustomerValidate
    ) {
        queryHandler.validateUpdate(dto, id)
    }
}