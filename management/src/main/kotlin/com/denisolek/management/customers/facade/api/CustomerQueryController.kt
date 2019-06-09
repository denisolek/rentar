package com.denisolek.management.customers.facade.api

import com.denisolek.management.customers.facade.CustomerQueryHandler
import com.denisolek.management.customers.facade.query.AddCustomerValidate
import com.denisolek.management.customers.facade.query.BaseCustomer
import com.denisolek.management.customers.facade.query.UpdateCustomerValidate
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CustomerQueryController(val queryHandler: CustomerQueryHandler) {
    companion object {
        const val CUSTOMER_ID = "customerId"
    }

    @PostMapping("/customers/validateAdd")
    fun validateAdd(@RequestBody dto: AddCustomerValidate) {
        queryHandler.validateAdd(dto)
    }

    @PostMapping("/customers/{$CUSTOMER_ID}/validateUpdate")
    fun validateUpdate(
        @PathVariable(required = true, value = CUSTOMER_ID) id: UUID,
        @RequestBody dto: UpdateCustomerValidate
    ) {
        queryHandler.validateUpdate(dto, id)
    }

    @GetMapping("/customers")
    fun fetchAll(): List<BaseCustomer> {
        return queryHandler.fetchAll()
    }
}