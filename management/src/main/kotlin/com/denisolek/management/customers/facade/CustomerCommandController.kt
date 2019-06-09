package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.command.AddCustomerCommand
import com.denisolek.management.customers.facade.command.CustomerCommandHandler
import com.denisolek.management.customers.facade.command.UpdateCustomerCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CustomerCommandController(val commandHandler: CustomerCommandHandler) {
    companion object {
        const val CUSTOMER_ID = "customerId"
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody addCustomerCommand: AddCustomerCommand): UUID {
        return commandHandler.handle(addCustomerCommand)
    }

    @PutMapping("/customers/{$CUSTOMER_ID}")
    fun updateCustomer(
        @PathVariable(required = true, value = CUSTOMER_ID) id: UUID,
        @RequestBody updateCustomerCommand: UpdateCustomerCommand
    ) {
        commandHandler.handle(updateCustomerCommand, id)
    }
}