package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.query.CustomerQueryHandler
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerQueryController(val queryHandler: CustomerQueryHandler) {
    private val logger = LoggerFactory.getLogger(javaClass)
}